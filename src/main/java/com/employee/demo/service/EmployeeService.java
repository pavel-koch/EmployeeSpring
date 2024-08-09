package com.employee.demo.service;

import com.employee.demo.Employee;

import java.util.Collection;

public interface  EmployeeService {
    public Employee addEmloyee(String firstName, String lastName, int department, double salary);
    public Employee removeEmployee(String firstName, String lastName);
    public Employee findEmployee(String firstName, String lastName);
    public Collection<Employee> findALlEmployee();
}
