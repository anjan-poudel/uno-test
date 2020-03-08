package com.uno.homeloans.web.controller;


import com.uno.homeloans.DateService;
import com.uno.homeloans.web.model.CalculateDateRequest;
import com.uno.homeloans.web.validation.ValidDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController()
@RequestMapping("/date")
@Valid
public class DateController {

    private DateService dateService;

    @Autowired
    public DateController(DateService dateService) {

        this.dateService = dateService;
    }

    @GetMapping("/difference/calculate")
    public long calculateDays(@RequestParam(name = "fromDate")
                              @DateTimeFormat(pattern = "dd.MM.yyyy", iso = DateTimeFormat.ISO.DATE)
                              @ValidDate LocalDate fromDate,
                              @RequestParam(name = "toDate")
                              @DateTimeFormat(pattern = "dd.MM.yyyy", iso = DateTimeFormat.ISO.DATE)
                              @ValidDate LocalDate toDate) {

        return dateService.calculateDays(new CalculateDateRequest(fromDate, toDate));
    }
}
