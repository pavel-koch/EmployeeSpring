package com.employee.demo.service;

import com.employee.demo.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    public Employee getMaxSalary(int department);
    public Employee getMinSalary(int department);
    public double getSumSalary(int department);

    public List<Employee> getAllEmployeesByDepartment(int department);

    public Map<Integer, List<Employee>> getAllEmployeesSortByDepartment();
}
