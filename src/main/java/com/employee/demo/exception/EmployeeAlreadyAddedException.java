package com.employee.demo.exception;

public class EmployeeAlreadyAddedException extends RuntimeException{
    public EmployeeAlreadyAddedException() {
        super("Уже есть такой сотрудник");
    }
}
