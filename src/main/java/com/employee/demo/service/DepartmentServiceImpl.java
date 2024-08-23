package com.employee.demo.service;

import com.employee.demo.Employee;
import com.employee.demo.exception.EmployeeNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private EmployeeServiceImpl employeeService;

    public DepartmentServiceImpl(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public double getSumSalary(int department) {
        return employeeService.findALlEmployee()
                .stream()
                .filter(e -> e.getDepartment() == department)
                .map(Employee::getSalary)
                .reduce(0d, Double::sum);
    }
    @Override
    public Employee getMaxSalary(int department) {
        return employeeService.findALlEmployee()
                .stream()
                .filter(e -> e.getDepartment() == department)
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }
    @Override
    public Employee getMinSalary(int department) {
        return employeeService.findALlEmployee()
                .stream()
                .filter(e -> e.getDepartment() == department)
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public List<Employee> getAllEmployeesByDepartment(int department) {
        return employeeService.findALlEmployee()
                .stream()
                .filter(e -> e.getDepartment() == department)
                .toList();
    }

    @Override
    public Map<Integer, List<Employee>> getAllEmployeesSortByDepartment() {
        return employeeService.findALlEmployee()
                .stream()
                .collect(Collectors.groupingBy(e -> e.getDepartment()));
    }

}

