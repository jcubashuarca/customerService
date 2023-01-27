package com.whiz.customermanagement.customerService.validation;

import com.whiz.customermanagement.customerService.config.validator.BirthDateConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.whiz.customermanagement.customerService.constant.Constants.BIRTH_DATE_PATTERN;

public class BirthDateValidator implements ConstraintValidator<BirthDateConstraint, String> {

    @Override
    public void initialize(BirthDateConstraint contactNumber) {
    }

    @Override
    public boolean isValid(String birthDate,
                           ConstraintValidatorContext cxt) {
        Pattern pattern = Pattern.compile(BIRTH_DATE_PATTERN);
        Matcher matcher = pattern.matcher(birthDate);
        return matcher.matches();
    }

}

