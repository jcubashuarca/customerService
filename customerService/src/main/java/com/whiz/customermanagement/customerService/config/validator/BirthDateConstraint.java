package com.whiz.customermanagement.customerService.config.validator;

import com.whiz.customermanagement.customerService.validation.BirthDateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = BirthDateValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface BirthDateConstraint {

    String message() default "Fecha naciemiento invalida";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
