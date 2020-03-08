package com.uno.homeloans.service;

import java.time.LocalDate;
import java.time.Month;

public class DateService {


    /**
     * anchor point for all date calcuulations
     */
    private static final  LocalDate _1900 = LocalDate.of(1900, 1, 1);


    public long calculateDays(LocalDate fromDate,
                              LocalDate toDate) {

        if (fromDate.equals(toDate)) {
            return 0;
        }
        return doCalculateDays(fromDate, toDate);


    }


    public long doCalculateDays(LocalDate fromDate,
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
    private long getDaysSinceBegining(LocalDate date) {

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
