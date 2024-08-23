package com.employee.demo.service;

import com.employee.demo.Employee;
import com.employee.demo.exception.EmployeeNotFoundException;
import com.employee.demo.exception.EmployeeStorageIsFullException;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.RandomUtils.nextDouble;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {
    private static final Faker faker = new Faker();
    @Mock
    private EmployeeServiceImpl employeeService;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    private static final Collection<Employee> employees = List.of(
            new Employee(faker.name().firstName(), faker.name().lastName(), nextInt(), nextDouble()),
            new Employee(faker.name().firstName(), faker.name().lastName(), nextInt(), nextDouble()),
            new Employee(faker.name().firstName(), faker.name().lastName(), nextInt(), nextDouble()));

    @Test
    void getSumSalaryCorrectData() {
        when(employeeService.findALlEmployee()).thenReturn(employees);
        int department = employees.iterator().next().getDepartment();
        double expected = 0;
        for (Employee employee : employees) {
            if (employee.getDepartment() == department) {
                expected += employee.getSalary();
            }
        }
        // test
        double actual = departmentService.getSumSalary(department);
        assertThat(actual).isEqualTo(expected);

    }
    @Test
    void getSumSalary_EmptyMap_ThenReturnZero() {
        when(employeeService.findALlEmployee()).thenReturn(List.of());

        // test
        double actual = departmentService.getSumSalary(1);
        assertThat(actual).isZero();

    }

    @Test
    void getMaxSalary() {
        when(employeeService.findALlEmployee()).thenReturn(employees);
        int department = employees.iterator().next().getDepartment();
        double expected = Double.MIN_VALUE;
        for (Employee employee : employees) {
            if ((employee.getDepartment() == department)&& employee.getSalary() > expected) {
                expected = employee.getSalary();
            }
        }
        // test
        double actual = departmentService.getMaxSalary(department).getSalary();
        assertThat(actual).isEqualTo(expected);

    }

    @Test
    void getMaxSalary_EmptyMap_ThenExceptions() {
        when(employeeService.findALlEmployee()).thenReturn(List.of());

        // test
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> departmentService.getMaxSalary(1).getSalary());

    }

    @Test
    void getMinSalary() {
        when(employeeService.findALlEmployee()).thenReturn(employees);
        int department = employees.iterator().next().getDepartment();
        double expected = Double.MAX_VALUE;
        for (Employee employee : employees) {
            if ((employee.getDepartment() == department)&& employee.getSalary() < expected) {
                expected = employee.getSalary();
            }
        }
        // test
        double actual = departmentService.getMinSalary(department).getSalary();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getMinSalary_EmptyMap_ThenExceptions() {
        when(employeeService.findALlEmployee()).thenReturn(List.of());

        // test
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> departmentService.getMinSalary(1).getSalary());
    }

    @Test
    void getAllEmployeesByDepartment() {
        when(employeeService.findALlEmployee()).thenReturn(employees);
        int department = employees.iterator().next().getDepartment();
        List<Employee> expected = employeeService.findALlEmployee()
                .stream()
                .filter(e -> e.getDepartment() == department)
                .toList();


        // test
        List<Employee> actual = departmentService.getAllEmployeesByDepartment(department);
        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);


    }

    @Test
    void getAllEmployeesSortByDepartment() {
        when(employeeService.findALlEmployee()).thenReturn(employees);
        Map<Integer, List<Employee>> expected = employeeService.findALlEmployee()
                .stream()
                .collect(Collectors.groupingBy(e -> e.getDepartment()));

        // test
        Map<Integer, List<Employee>> actual = departmentService.getAllEmployeesSortByDepartment();
        assertThat(actual).containsExactlyInAnyOrderEntriesOf(expected);
    }
}