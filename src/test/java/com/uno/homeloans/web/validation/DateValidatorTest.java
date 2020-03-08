package com.uno.homeloans.web.validation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintValidatorContext;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DateValidatorTest {

    @Mock
    ConstraintValidatorContext ctx;

    DateValidator validator = new DateValidator();

    @Test
    public void testIsValid() {
        LocalDate from = LocalDate.now();
        LocalDate to = from.plusDays(15);
        assertTrue(validator.isValid(from, ctx));
        assertTrue(validator.isValid(to, ctx));
    }

    @Test
    public void testIsValid_false() {
        LocalDate from = LocalDate.of(1801, 1, 1);
        LocalDate to = from.plusDays(15);
        assertFalse(validator.isValid(from, ctx));
        assertFalse(validator.isValid(to, ctx));
    }

}
