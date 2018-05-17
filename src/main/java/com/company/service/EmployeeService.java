package com.company.service;

import com.company.dao.EmployeeDao;
import com.company.model.Employee;

public class EmployeeService extends EntityService<Employee, EmployeeDao> {

    private static EmployeeDao dao = new EmployeeDao();

    @Override
    EmployeeDao getDao() {
        return dao;
    }

}
