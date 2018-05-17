package com.company.dao;

import com.company.model.Employee;
import org.bson.Document;

public class EmployeeDao extends EntityDao<Employee> {


    @Override
    String getCollectionName() {
        return "employees";
    }


    @Override
    protected Employee createEntityWithDocument(Document document) {
        Employee employee = new Employee();
        employee.setName((String) document.get("name")  );

        return employee;
    }


    @Override
    protected void updateDocumentWithEntity(Document document, Employee entity) {
        document.append("name", entity.getName());
    }
}
