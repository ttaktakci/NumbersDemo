package com.company.resource;

import com.company.model.Employee;
import com.company.service.EmployeeService;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/employees")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeResource {

    private static EmployeeService employeeService = new EmployeeService();

    @GET
    public List<Employee> getList(@QueryParam("order") String order) {
        return employeeService.getAllItems(order);
    }

    @GET
    @Path("/{number}")
    public Employee get(@PathParam("number") long number) {
        return employeeService.getItem(number);
    }

    @POST
    public Response add(Employee employee) {
        Employee newEmployee = employeeService.addItem(employee);
        return Response.status(Response.Status.CREATED)
                .entity(newEmployee)
                .build();
    }

    @PUT
    @Path("/{number}")
    public Employee update(@PathParam("number") long number, Employee employee) {
        throw new WebApplicationException(Response.Status.METHOD_NOT_ALLOWED);
    }

    @DELETE
    @Path("/{number}")
    public Employee delete(@PathParam("number") long number) {
        return employeeService.deleteItem(number);
    }

    @GET
    @Path("/max")
    public Employee getEmployeeWithMaxNumber() {
        return employeeService.getItemMax();
    }

    @GET
    @Path("/min")
    public Employee getEmployeeWithMinNumber() {
        return employeeService.getItemMin();
    }
}
