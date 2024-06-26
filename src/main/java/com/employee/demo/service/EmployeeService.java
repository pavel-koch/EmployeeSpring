package com.employee.demo.service;

public interface  EmployeeService {
    public String addEmloyee(String firstName, String lastName);
    public String removeEmployee(String firstName, String lastName);
    public String findEmployee(String firstName, String lastName);
    public String printALlEmployee();
}
