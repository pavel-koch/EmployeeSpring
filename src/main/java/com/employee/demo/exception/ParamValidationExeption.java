package com.employee.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ParamValidationExeption extends RuntimeException {
    public ParamValidationExeption(String param) {
        super("Invalid parameter: %s".formatted(param));
    }
}
