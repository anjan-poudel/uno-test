package com.uno.homeloans.web.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.uno.homeloans.web.model.jackson.LocalDateSerialiser;
import com.uno.homeloans.web.validation.ValidDate;
import org.springframework.validation.annotation.Validated;


import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Validated
public class CalculateDateRequest {


    @NotNull
   //@DateTimeFormat(pattern = "dd.MM.yyyy", iso = DateTimeFormat.ISO.DATE)
    @JsonDeserialize(using = LocalDateSerialiser.class)
    @ValidDate
    private  LocalDate fromDate;

    @NotNull
    //@DateTimeFormat(pattern = "dd.MM.yyyy", iso = DateTimeFormat.ISO.DATE)
    @JsonDeserialize(using = LocalDateSerialiser.class)
    @ValidDate
    private  LocalDate toDate;



    public LocalDate getFromDate() {
        return fromDate;
    }


    @JsonDeserialize(using = LocalDateSerialiser.class)
    public void setFromDate(final LocalDate fromDate) {
        this.fromDate = fromDate;
    }


    public LocalDate getToDate() {
        return toDate;
    }

    @JsonDeserialize(using = LocalDateSerialiser.class)
    public void setToDate(final LocalDate toDate) {
        this.toDate = toDate;
    }


}
