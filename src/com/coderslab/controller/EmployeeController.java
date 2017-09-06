package com.coderslab.controller;

import com.coderslab.dao.EmployeeDao;
import com.coderslab.model.Employee;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeDao employeeDao;

    public EmployeeDao getEmployeeDao() {
        return employeeDao;
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public List<Employee> getAllEmployee() {
        List<Employee> list = getEmployeeDao().getAllEmployee();
        return list;
    }

    public boolean saveEmployee(Employee employee) {
        boolean status = getEmployeeDao().saveEmployee(employee);
        return status;
    }

    public boolean updateEmployee(Employee employee) {
        boolean status = getEmployeeDao().updateEmployee(employee);
        return status;
    }

    public boolean deleteEmployee(Employee employee) {
        boolean status = getEmployeeDao().deleteEmployee(employee);
        return status;
    }

}
