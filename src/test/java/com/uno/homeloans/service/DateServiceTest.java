package com.uno.homeloans.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DateServiceTest {

    private DateService dateServiceUnderTest;

    @BeforeEach
    void setUp() {
        dateServiceUnderTest = new DateService();
    }

    @Test
    void testGetDaysSinceBegining() {
        long result = dateServiceUnderTest.getDaysSinceBegining(LocalDate.of(1900, 1, 11));
        assertEquals(10L, result);

        //1904 is leap year
        result = dateServiceUnderTest.getDaysSinceBegining(LocalDate.of(1905, 1, 1));
        assertEquals((365 * 4) + 366, result);
    }

    @Test
    void testValidatino() {
        assertThrows(IllegalStateException.class, () -> dateServiceUnderTest.calculateDays(null, LocalDate.of(2017, 1, 3)));
        assertThrows(IllegalStateException.class, () -> dateServiceUnderTest.calculateDays(LocalDate.of(2017, 1, 3), null));
        assertThrows(IllegalStateException.class, () -> dateServiceUnderTest.calculateDays(null, null));
    }

    @Test
    void testCalculateDays_positive() {
        final long result = dateServiceUnderTest.calculateDays(
                LocalDate.of(2017, 1, 1),
                LocalDate.of(2017, 1, 3));

        assertEquals(1L, result);
    }

    @Test
    void testCalculateDays_february() {
        final long result = dateServiceUnderTest.calculateDays(
                LocalDate.of(2017, 2, 1),
                LocalDate.of(2017, 3, 1));

        assertEquals(27L, result);
    }

    @Test
    void testCalculateDays_leapYear() {
        final long result = dateServiceUnderTest.calculateDays(
                LocalDate.of(1904, 2, 1),
                LocalDate.of(1904, 3, 1));

        assertEquals(28L, result);
    }

    @Test
    void testCalculateDays_negative() {
        final long result = dateServiceUnderTest.calculateDays(
                LocalDate.of(2017, 1, 3),
                LocalDate.of(2017, 1, 1));

        assertEquals(-1L, result);
    }

}
