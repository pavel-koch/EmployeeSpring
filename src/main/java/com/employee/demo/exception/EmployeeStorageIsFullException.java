package com.employee.demo.exception;

public class EmployeeStorageIsFullException extends RuntimeException{
    public EmployeeStorageIsFullException() {
        super("Превышен лимит количества сотрудников");
    }
}
