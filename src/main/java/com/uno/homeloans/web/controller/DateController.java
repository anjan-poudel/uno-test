package com.uno.homeloans.web.controller;

import com.uno.homeloans.service.DateService;
import com.uno.homeloans.web.model.CalculateDateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/date")
public class DateController {

    private DateService dateService;

    @Autowired
    public DateController(DateService dateService) {

        this.dateService = dateService;
    }

    @GetMapping("/difference")
    public long calculateDiff2(@RequestBody @Valid @ModelAttribute CalculateDateRequest calculateDateRequest) {

        return dateService.calculateDays(calculateDateRequest.getFromDate(), calculateDateRequest.getToDate());
    }


}
