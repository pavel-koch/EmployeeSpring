package com.employee.demo.validation;

import com.employee.demo.exception.ParamValidationExeption;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Locale;
@Component
public class Validator {
    public String checkAndCapitalize(String param) {
        if (!StringUtils.isAlpha(param)) {
            throw new ParamValidationExeption(param);
        }
        return StringUtils.capitalize(param.toLowerCase());
    }
}
