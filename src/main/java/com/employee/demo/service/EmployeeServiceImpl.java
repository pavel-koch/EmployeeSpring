package com.employee.demo.service;

import com.employee.demo.Employee;
import com.employee.demo.exception.EmployeeAlreadyAddedException;
import com.employee.demo.exception.EmployeeNotFoundException;
import com.employee.demo.exception.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private final int maxEmployee = 7;
    private int count = 0;
    List <Employee> employees = new ArrayList<>();

    public String addEmloyee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException();
        }
        if (count >= maxEmployee) {
            throw new EmployeeStorageIsFullException();
        }
        employees.add(employee);
        count++;
        return employee.toString();
    }

    public String removeEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.contains(employee)) {
            throw new EmployeeNotFoundException();
        }
        employees.remove((Employee) employee);
        count--;
        return employee.toString();
    }

    public String findEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.contains(employee)) {
            throw new EmployeeNotFoundException();
        }
        return employee.toString();
    }

    public String printALlEmployee() {
        return employees.toString();
    }
}
