package com.uno.homeloans.web.validation;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

/*
Validates date so that requested dates are bigger than 01.01.1900
 */
@Component
public class DateValidator implements ConstraintValidator<ValidDate, LocalDate> {

    private static LocalDate MIN_DATE = LocalDate.of(1900, 01, 01);

    @Override
    public boolean isValid(final LocalDate localDate, final ConstraintValidatorContext constraintValidatorContext) {

       return localDate.isAfter(MIN_DATE);

    }

}
