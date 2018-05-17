package com.company.service;

import com.company.dao.EmployeeDao;
import com.company.exception.ItemAlreadyAvailableException;
import com.company.exception.ItemNotFoundException;
import com.company.model.Employee;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class EmployeeServiceTest {

    private EmployeeService target = null;
    private EmployeeDao mockEmployeeDao = null;

    @Before
    public void setUp() throws Exception {
        target = new EmployeeService();
        mockEmployeeDao = Mockito.mock(EmployeeDao.class);
        EmployeeService.dao = mockEmployeeDao;
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAllItems_success() {
        //setup
        List<Employee> employeeListFixture = new ArrayList<>();
        employeeListFixture.add(new Employee(1234L, "John", "2018-05-17 06:13:24"));
        employeeListFixture.add(new Employee(246L, "Mike", "2017-04-11 08:11:14"));
        Mockito.when(mockEmployeeDao.getList("desc")).thenReturn(employeeListFixture);

        //execution
        List<Employee> itemList = target.getAllItems("desc");

        //verification
        Assert.assertNotNull(itemList);
        Assert.assertEquals(itemList.size(), 2);
        Assert.assertTrue("John".equals(itemList.get(0).getName()));
        Assert.assertEquals(246L, itemList.get(1).getNumber());
        Assert.assertTrue("2017-04-11 08:11:14".equals(itemList.get(1).getInsertDate()));

    }

    @Test
    public void getItem_success() {
        //setup
        Employee employeeFixture = new Employee(1234L, "John", "2018-05-17 06:13:24");
        Mockito.when(mockEmployeeDao.get(1234L)).thenReturn(employeeFixture);

        //execution
        Employee item = target.getItem(1234L);

        //verification
        Assert.assertNotNull(item);
        Assert.assertTrue("John".equals(item.getName()));
        Assert.assertEquals(1234L, item.getNumber());
        Assert.assertTrue("2018-05-17 06:13:24".equals(item.getInsertDate()));

    }

    @Test(expected = ItemNotFoundException.class)
    public void getItem_throws_exception() {
        //setup
        Mockito.when(mockEmployeeDao.get(1234L)).thenReturn(null);

        //execution
        Employee item = target.getItem(1234L);
    }

    @Test(expected = RuntimeException.class)
    public void addItem_throws_exception_when_number_0() {
        //setup
        Employee employee = new Employee(0, "Jack", null);

        //execution
        target.addItem(employee);
    }

    @Test(expected = RuntimeException.class)
    public void addItem_throws_exception_when_number_negative() {
        //setup
        Employee employee = new Employee(-1, "Jack", null);

        //execution
        target.addItem(employee);
    }

    @Test(expected = ItemAlreadyAvailableException.class)
    public void addItem_throws_exception_when_item_already_available() {
        //setup
        Employee employeeFixture = new Employee(1234L, "John", "2018-05-17 06:13:24");
        Mockito.when(mockEmployeeDao.get(1234L)).thenReturn(employeeFixture);

        //execution
        target.addItem(new Employee(1234L, "Jack", null));
    }

    @Test
    public void addItem_success() {
        //setup
        Employee employee = new Employee(1234L, "John", null);
        Employee employeeFixture = new Employee(1234L, "John", "2018-05-17 06:13:24");
        Mockito.when(mockEmployeeDao.add(employee)).thenReturn(employeeFixture);

        //execution
        Employee newEmployee = target.addItem(employee);

        //verification
        Assert.assertNotNull(newEmployee);
        Assert.assertEquals(newEmployee.getNumber(), employeeFixture.getNumber());
        Assert.assertTrue(newEmployee.getName().equals(employeeFixture.getName()));
        Assert.assertTrue(newEmployee.getInsertDate().equals(employeeFixture.getInsertDate()));

    }

    @Test(expected = RuntimeException.class)
    public void deleteItem_throws_exception_when_number_negative() {
        //execution
        target.deleteItem(-5L);
    }

    @Test(expected = ItemNotFoundException.class)
    public void deleteItem_throws_exception_if_employee_not_valid() {
        //setup
        Mockito.when(mockEmployeeDao.get(1234L)).thenReturn(null);

        //execution
        Employee item = target.deleteItem(1234L);
    }

    @Test
    public void deleteItem_success() {
        //setup
        Employee employee = new Employee(1234L, "John", "2018-05-17 06:13:24");
        Mockito.when(mockEmployeeDao.get(1234L)).thenReturn(employee);
        Mockito.when(mockEmployeeDao.remove(employee)).thenReturn(employee);

        //execution
        Employee deletedEmployee = target.deleteItem(1234L);

        //verification
        Assert.assertNotNull(deletedEmployee);
        Assert.assertEquals(deletedEmployee.getNumber(), employee.getNumber());
    }

    @Test
    public void getItemMax() {
        //setup
        Employee employee = new Employee(9999L, "Carl", "2008-11-17 11:47:09");
        Mockito.when(mockEmployeeDao.getMax()).thenReturn(employee);

        //execution
        Employee maxEmployee = target.getItemMax();

        //verification
        Assert.assertNotNull(maxEmployee);
        Assert.assertEquals(maxEmployee.getNumber(), employee.getNumber());
    }

    @Test
    public void getItemMin() {
        //setup
        Employee employee = new Employee(1L, "Suzie", "2018-11-17 08:47:09");
        Mockito.when(mockEmployeeDao.getMin()).thenReturn(employee);

        //execution
        Employee minEmployee = target.getItemMin();

        //verification
        Assert.assertNotNull(minEmployee);
        Assert.assertEquals(minEmployee.getNumber(), employee.getNumber());
    }
}