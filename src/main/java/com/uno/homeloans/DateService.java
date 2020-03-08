package com.uno.homeloans;

import com.uno.homeloans.web.model.CalculateDateRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/*
2019-10-12 2020-01-01

 */
@Service
public class DateService {

    public long calculateDays(LocalDate fromDate,
                              LocalDate toDate) {

        if (fromDate.equals(toDate)) {
            return 0;
        }
        long numberOfDays = 0;

        boolean sameYear = toDate.getYear() == fromDate.getYear();
        boolean lessThanAYear = fromDate.getYear() >= toDate.getYear();

        boolean negative = toDate.isBefore(fromDate);


        if (!sameYear) {

            LocalDate date = fromDate;
            for (; ; ) {
                if (negative) {
                    numberOfDays -= date.lengthOfYear();
                    date = date.minusYears(1);
                } else {
                    numberOfDays += date.lengthOfYear();
                    date = date.plusYears(1);
                }

                if (date.equals(toDate)) {
                    break;
                }
            }
        }

        boolean sameMonth = toDate.getMonth() == fromDate.getMonth();

        boolean lessThanAMonth = fromDate.getMonth().getValue() >= toDate.getMonth().getValue();


        if (!sameMonth && !lessThanAMonth) {
            LocalDate date = fromDate;
            for (; ; ) {

                if (negative) {
                    numberOfDays -= date.lengthOfMonth();
                    date = date.minusMonths(1);
                } else {
                    numberOfDays += date.lengthOfMonth();
                    date = date.plusMonths(1);
                }
                if (date.getMonth() == toDate.getMonth()) {
                    break;
                }
            }
        }

        if (!lessThanAMonth) {
            LocalDate date = fromDate;
            for (; ; ) {

                if (negative) {
                    numberOfDays--;
                    date = date.minusDays(1);
                } else {
                    numberOfDays++;
                    date = date.plusDays(1);
                }
                if (date.getMonth() == toDate.getMonth()) {
                    break;
                }
            }
        }

        return numberOfDays - 1;
    }

    public long calculateDays(CalculateDateRequest calculateDateRequest) {


        long numberOfDays = calculateDateRequest.getFromDate().toEpochDay() - calculateDateRequest.getToDate().toEpochDay();
        return numberOfDays - 1;
    }
}
