package com.employee.demo.controller;

import com.employee.demo.Employee;
import com.employee.demo.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    @GetMapping("/{id}/salary/sum")
    public double getSumSalary(@PathVariable("department") int department) {
        return departmentService.getSumSalary(department);
    }
    @GetMapping("/{id}/salary/max")
    public Employee getMaxSalary(@PathVariable("department") int department) {
        return departmentService.getMaxSalary(department);
    }

    @GetMapping("/{id}/salary/min")
    public Employee getMinSalary(@PathVariable("department") int department) {
        return departmentService.getMinSalary(department);
    }
    @GetMapping("/{id}/employees")
    public List<Employee> getAllEmployeesByDepartment(@PathVariable("department") int department) {
        return departmentService.getAllEmployeesByDepartment(department);
    }

    @GetMapping("/employees")
    public Map<Integer, List<Employee>> getAllEmployeesSortByDepartment() {
        return departmentService.getAllEmployeesSortByDepartment();
    }
}
