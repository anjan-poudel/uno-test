package com.uno.homeloans.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DateServiceTest {

    private DateService dateServiceUnderTest;

    @BeforeEach
    void setUp() {
        dateServiceUnderTest = new DateService();
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
