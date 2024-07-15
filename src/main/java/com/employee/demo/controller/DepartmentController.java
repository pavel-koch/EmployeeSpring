package com.employee.demo.controller;

import com.employee.demo.Employee;
import com.employee.demo.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/max-salary")
    public Employee getMaxSalary(@RequestParam("department") int department) {
        return departmentService.getMaxSalary(department);
    }

    @GetMapping("/min-salary")
    public Employee getMinSalary(@RequestParam("department") int department) {
        return departmentService.getMinSalary(department);
    }
    @GetMapping("/all")
    public List<Employee> getAllEmployeesByDepartment(@RequestParam("department") int department) {
        return departmentService.getAllEmployeesByDepartment(department);
    }

    @GetMapping("/alll")
    public Map<Integer, List<Employee>> getAllEmployeesSortByDepartment() {
        return departmentService.getAllEmployeesSortByDepartment();
    }
}
