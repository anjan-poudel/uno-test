package com.uno.homeloans.web.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class DateValidator implements ConstraintValidator<ValidDate, LocalDate> {

    private static LocalDate MIN_DATE = LocalDate.of(1900, 01, 01);

    @Override
    public boolean isValid(final LocalDate localDate, final ConstraintValidatorContext constraintValidatorContext) {

        return localDate.equals(MIN_DATE) || localDate.isAfter(MIN_DATE);
    }
}
