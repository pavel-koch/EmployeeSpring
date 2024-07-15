package com.employee.demo.controller;

import com.employee.demo.Employee;
import com.employee.demo.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String employee() {
        return "Добро пожаловать в компанию";
    }

    @GetMapping("/add")
    public Employee addEmloyee(@RequestParam ("firstName") String firstName,
                               @RequestParam ("lastName") String lastName,
                               @RequestParam ("department") int department,
                               @RequestParam ("salary") double salary) {
        return employeeService.addEmloyee(firstName, lastName, department, salary);
    }

    @GetMapping("/remove")
    public Employee removeEmployee(@RequestParam ("firstName") String firstName,
                           @RequestParam ("lastName") String lastName) {
        return employeeService.removeEmployee(firstName, lastName);
    }
    @GetMapping("/find")
    public Employee findEmployee(@RequestParam ("firstName") String firstName,
                               @RequestParam ("lastName") String lastName) {
        return employeeService.findEmployee(firstName, lastName);
    }

    @GetMapping("/print")
    public Collection<Employee> printAllEmployee() {
        return employeeService.findALlEmployee();
    }
}
