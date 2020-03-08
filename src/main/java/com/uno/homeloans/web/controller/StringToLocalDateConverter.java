package com.uno.homeloans.web.controller;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
public class StringToLocalDateConverter implements Converter<String, LocalDate> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @Override
    public LocalDate convert(String source) {
        try {
            return LocalDate.parse(
                    source, formatter
            );
        } catch (DateTimeParseException ex) {

            throw new IllegalArgumentException("invalid date format:" + source, ex);
        }
    }


}
