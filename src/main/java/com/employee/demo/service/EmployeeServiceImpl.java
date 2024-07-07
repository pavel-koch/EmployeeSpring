package com.employee.demo.service;

import com.employee.demo.Employee;
import com.employee.demo.exception.EmployeeAlreadyAddedException;
import com.employee.demo.exception.EmployeeNotFoundException;
import com.employee.demo.exception.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final int maxEmployee = 7;
    private int count = 0;
    Map<String, Employee> employees;

    public EmployeeServiceImpl() {
        this.employees = new HashMap<>();
    }

    @Override
    public Employee addEmloyee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException();
        }
        if (count >= maxEmployee) {
            throw new EmployeeStorageIsFullException();
        }
        employees.put(employee.getFullName(), employee);
        count++;
        return employee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.containsKey(employee.getFullName())) {
            throw new EmployeeNotFoundException();
        }
        employees.remove(employee.getFullName());
        count--;
        return employee;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.containsKey(employee.getFullName())) {
            throw new EmployeeNotFoundException();
        }
        return employees.get(employee.getFullName());
    }

    @Override
    public Collection<Employee> printALlEmployee() {
        return Collections.unmodifiableCollection(employees.values());
    }
}
