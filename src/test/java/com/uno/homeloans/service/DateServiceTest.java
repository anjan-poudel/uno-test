package com.uno.homeloans.service;

import com.uno.homeloans.repository.CalculationResultRepository;
import com.uno.homeloans.web.model.CalculationResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DateServiceTest {

    @Mock
    private CalculationResultRepository repository;
    @InjectMocks
    private DateService dateService;


    @Test
    void testGetDaysSinceBegining() {
        long result = dateService.getDaysSinceBegining(LocalDate.of(1900, 1, 11));
        assertEquals(10L, result);

        //1904 is leap year
        result = dateService.getDaysSinceBegining(LocalDate.of(1905, 1, 1));
        assertEquals((365 * 4) + 366, result);
    }

    @Test
    void testValidatino() {
        assertThrows(IllegalStateException.class, () -> dateService.calculateDays(null, LocalDate.of(2017, 1, 3)));
        assertThrows(IllegalStateException.class, () -> dateService.calculateDays(LocalDate.of(2017, 1, 3), null));
        assertThrows(IllegalStateException.class, () -> dateService.calculateDays(null, null));
    }

    @Test
    void testCalculateDays_positive() {
        final long result = dateService.calculateDays(
                LocalDate.of(2017, 1, 1),
                LocalDate.of(2017, 1, 3));

        assertEquals(1L, result);
    }

    @Test
    void testCalculateDays_february() {
        final long result = dateService.calculateDays(
                LocalDate.of(2017, 2, 1),
                LocalDate.of(2017, 3, 1));

        assertEquals(27L, result);
    }

    @Test
    void testCalculateDays_leapYear() {
        final long result = dateService.calculateDays(
                LocalDate.of(1904, 2, 1),
                LocalDate.of(1904, 3, 1));

        assertEquals(28L, result);
    }

    @Test
    void testCalculateDays_negative() {
        final long result = dateService.calculateDays(
                LocalDate.of(2017, 1, 3),
                LocalDate.of(2017, 1, 1));

        assertEquals(-1L, result);
    }

    @Test
    void testCalculateDays_repositoryInteraction() {
        dateService.calculateDays(
                LocalDate.of(2017, 1, 3),
                LocalDate.of(2017, 1, 1));
        verify(repository, times(1)).save(any(CalculationResult.class));
    }

}
