package com.uno.homeloans.service;

import com.uno.homeloans.repository.CalculationResultRepository;
import com.uno.homeloans.web.model.CalculationResult;
import org.apache.http.util.Asserts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Service
public class DateService {

    private CalculationResultRepository resultRepository;

    @Autowired
    public DateService(CalculationResultRepository resultRepository) {

        this.resultRepository = resultRepository;
    }

    /**
     * anchor point for all date calcuulations
     */
    private static final LocalDate _1900 = LocalDate.of(1900, 1, 1);


    public long calculateDays(LocalDate fromDate,
                              LocalDate toDate) {

        Asserts.notNull(fromDate, "from date is required");
        Asserts.notNull(toDate, "to date is required");

        if (fromDate.equals(toDate)) {
            return 0;
        }
        long value = doCalculateDays(fromDate, toDate);
        CalculationResult result = new CalculationResult();
        result.setId(String.format("%s_%s", fromDate.format(DateTimeFormatter.BASIC_ISO_DATE),
                toDate.format(DateTimeFormatter.BASIC_ISO_DATE)));
        result.setValue(value);
        resultRepository.save(result);
        return value;
    }


    private long doCalculateDays(LocalDate fromDate,
                                 LocalDate toDate) {

        long daysSinceFromDate = getDaysSinceBegining(fromDate);
        long daysSinceToDate = getDaysSinceBegining(toDate);
        if (fromDate.isBefore(toDate)) {
            return daysSinceToDate - daysSinceFromDate - 1;
        } else {
            return (daysSinceFromDate - daysSinceToDate - 1) * -1;
        }

    }

    /**
     * 2020-01-25 2020-02-15
     *
     * @param date
     * @return
     */

    long getDaysSinceBegining(LocalDate date) {

        long numOfDays = 0;

        LocalDate dt = _1900;

        while (dt.getYear() < date.getYear()) {
            numOfDays += dt.lengthOfYear();
            dt = dt.plusYears(1);
        }
        dt = _1900;
        while (dt.getMonth().getValue() < date.getMonthValue()) {
            numOfDays += dt.lengthOfMonth();
            dt = dt.plusMonths(1);
        }

        dt = _1900;
        while (dt.getDayOfMonth() < date.getDayOfMonth()) {
            numOfDays++;
            dt = dt.plusDays(1);
        }

        if (date.isLeapYear() && date.getMonth().equals(Month.FEBRUARY)) {
            numOfDays--;
        }

        return numOfDays;//start and end

    }


}
