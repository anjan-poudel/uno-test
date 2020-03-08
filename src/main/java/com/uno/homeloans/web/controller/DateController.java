package com.uno.homeloans.web.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController("/date")
public class DateController {

    @GetMapping("/difference/calculate")
    public long calculateDays(@RequestParam( name = "fromDate") LocalDate fromDate, @RequestParam( name = "toDate") LocalDate toDate){

        long numberOfDays = toDate.toEpochDay() - fromDate.toEpochDay() ;
        return  numberOfDays -1;
    }
}
