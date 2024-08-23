package com.employee.demo.service;

import com.employee.demo.Employee;
import com.employee.demo.exception.EmployeeAlreadyAddedException;
import com.employee.demo.exception.EmployeeNotFoundException;
import com.employee.demo.exception.EmployeeStorageIsFullException;
import com.employee.demo.validation.Validator;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.apache.commons.lang3.RandomUtils.nextDouble;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.assertj.core.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class EmployeeServiceImplTest {
    private final Faker faker = new Faker();
    private final EmployeeServiceImpl employeeService = new EmployeeServiceImpl(new Validator());

    @Test
    void addEmloyeeCorrectParam() {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        int department = nextInt();
        double salary = nextDouble();

        //test
        Employee actual = employeeService.addEmloyee(firstName, lastName, department, salary);

        //check
        assertThat(actual.getFirstName()).isEqualTo(firstName);
        assertThat(actual.getLastName()).isEqualTo(lastName);
        assertThat(actual.getDepartment()).isEqualTo(department);
        assertThat(actual.getSalary()).isEqualTo(salary);
    }

    @Test
    void addEmloyeeToManyEmployeeAndThrowException() {

        //test
        for (int i = 0; i < employeeService.getMaxEmployee(); i++) {
            Employee actual = employeeService.addEmloyee(
                    faker.name().firstName(),
                    faker.name().lastName(),
                    nextInt(),
                    nextDouble());

        }

        //check
        assertThatExceptionOfType(EmployeeStorageIsFullException.class)
                .isThrownBy(() -> employeeService.addEmloyee(faker.name().firstName(),
                        faker.name().lastName(), nextInt(), nextDouble()));
    }

    @Test
    void addEmloyeeWhenEmployeeAddAndThrowException() {

        //test
        Employee expected = employeeService.addEmloyee(faker.name().firstName(),
                faker.name().lastName(), nextInt(), nextDouble());

        //check
        assertThatExceptionOfType(EmployeeAlreadyAddedException.class)
                .isThrownBy(() -> employeeService.addEmloyee(expected.getFirstName(),
                        expected.getLastName(), expected.getDepartment(), expected.getSalary()));
    }

    @Test
    void removeEmployeeCorrectParam() {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        int department = nextInt();
        double salary = nextDouble();
        Employee expected = employeeService.addEmloyee(firstName, lastName, department, salary);

        //test
        Employee actual = employeeService.removeEmployee(firstName, lastName);
        actual.setDepartment(department);
        actual.setSalary(salary);
        //check
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void removeEmployeeThrowException() {

        //test && check
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.removeEmployee(faker.name().firstName(),
                        faker.name().lastName()));

    }

    @Test
    void findEmployeeCorrectParam() {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        int department = nextInt();
        double salary = nextDouble();
        Employee expected = employeeService.addEmloyee(firstName, lastName, department, salary);

        //test
        Employee actual = employeeService.findEmployee(firstName, lastName);

        //check
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void findEmployeeThrowException() {
        //test && check
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.findEmployee(faker.name().firstName(),
                        faker.name().lastName()));

    }

    @Test
    void findALlEmployeeNull() {
        Collection<Employee> actual = employeeService.findALlEmployee();
        assertThat(actual).isEmpty();
    }

    @Test
    void findALlEmployee() {
        List<Employee> expected = new ArrayList<>();
        for (int i = 0; i < nextInt(1, employeeService.getMaxEmployee()); i++) {
            expected.add(employeeService.addEmloyee(
                    faker.name().firstName(),
                    faker.name().lastName(),
                    nextInt(),
                    nextDouble()));
        }
        Collection<Employee> actual = employeeService.findALlEmployee();
        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }
}