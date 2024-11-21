package com.plannerapp.service.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class DateInFutureValidator implements ConstraintValidator<DateInFuture, String> {
    @Override
    public void initialize(DateInFuture constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {

        if (value != null && !value.isBlank()) {

            LocalDate parse = LocalDate.parse(value);
            return parse.isAfter(LocalDate.now());
        }

        return false;
    }
}
