package com.uno.homeloans;


import org.junit.jupiter.api.Test;;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DateServiceTest {

    DateService dateService = new DateService();

    @Test
    public void testCalculateDays() {
        // Setup

        // Run the test
        final long result = dateService.calculateDays(LocalDate.of(2017, 1, 1), LocalDate.of(2017, 7, 1));

        System.out.println("result:" + result);
    }
}
