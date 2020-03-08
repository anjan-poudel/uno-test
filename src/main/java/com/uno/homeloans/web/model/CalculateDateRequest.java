package com.uno.homeloans.web.model;

import com.uno.homeloans.web.validation.ValidDate;

import java.time.LocalDate;

public class CalculateDateRequest {

    @ValidDate
    LocalDate fromDate;
    @ValidDate
    LocalDate toDate;

    public CalculateDateRequest(final LocalDate fromDate, final LocalDate toDate) {
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }
}
