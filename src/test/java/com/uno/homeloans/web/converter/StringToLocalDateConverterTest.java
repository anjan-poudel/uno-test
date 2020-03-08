package com.uno.homeloans.web.converter;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

class StringToLocalDateConverterTest {

    private StringToLocalDateConverter converter = new StringToLocalDateConverter();

    @Test
    public void testConvert() {
        LocalDate expected = LocalDate.of(2020, 2, 14);
         LocalDate result = converter.convert("14.02.2020");
        assertEquals(expected, result);
    }

    @Test
    public void testConvert_invalidFormat() {
       assertThrows(DateTimeParseException.class,()-> converter.convert("14.02.2020222"));

    }


}
