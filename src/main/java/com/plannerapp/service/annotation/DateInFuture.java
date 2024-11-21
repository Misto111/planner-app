package com.plannerapp.service.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = DateInFutureValidator.class)
public @interface DateInFuture {

    String message() default "Date is not in the future";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
