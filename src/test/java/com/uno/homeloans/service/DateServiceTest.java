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
        // Setup

        // Run the test
        final long result = dateServiceUnderTest.calculateDays(
                LocalDate.of(2017, 1, 1),
                LocalDate.of(2017, 1, 3));

        // Verify the results
        //assertEquals(0L, result);
        assertEquals(1L, result);
    }

    @Test
    void testCalculateDays_february() {
        // Setup

        // Run the test
        final long result = dateServiceUnderTest.calculateDays(
                LocalDate.of(2017, 2, 1),
                LocalDate.of(2017, 3, 1));

        // Verify the results
        //assertEquals(0L, result);
        assertEquals(27L, result);
    }

    @Test
    void testCalculateDays_leapYear() {
        // Setup

        // Run the test
        final long result = dateServiceUnderTest.calculateDays(
                LocalDate.of(1904, 2, 1),
                LocalDate.of(1904, 3, 1));

        // Verify the results
        //assertEquals(0L, result);
        assertEquals(28L, result);
    }

    @Test
    void testCalculateDays_negative() {
        // Setup

        // Run the test
        final long result = dateServiceUnderTest.calculateDays(
                LocalDate.of(2017, 1, 3),
                LocalDate.of(2017, 1, 1));

        // Verify the results
        //assertEquals(0L, result);
        assertEquals(-1L, result);
    }

}
