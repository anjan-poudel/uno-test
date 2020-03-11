package com.uno.homeloans.web.controller;



import com.uno.homeloans.service.DateService;
import com.uno.homeloans.web.model.CalculateDateRequest;
import com.uno.homeloans.web.validation.DateValidator;
import com.uno.homeloans.web.validation.ValidDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
@RequestMapping("/date")
public class DateController {

    private DateService dateService;

    @Autowired
    public DateController(DateService dateService) {

        this.dateService = dateService;
    }

//    @InitBinder
//    protected void initBinder(WebDataBinder binder) {
//        binder.setValidator(new DateValidator());
//    }

    @Valid
    @GetMapping("/difference")
    public long calculateDays(@RequestParam(name = "fromDate")
                                  @ValidDate  @DateTimeFormat(pattern = "dd.MM.yyyy", iso = DateTimeFormat.ISO.DATE)
                            @Valid  LocalDate fromDate,
                              @RequestParam(name = "toDate")
                              @DateTimeFormat(pattern = "dd.MM.yyyy", iso = DateTimeFormat.ISO.DATE)
                              @ValidDate  @Valid LocalDate toDate) {

        return dateService.calculateDays(fromDate, toDate);
    }


    @PostMapping("/diff")
    public long calculateDiff(@RequestBody @Valid  CalculateDateRequest calculateDateRequest) {

        return dateService.calculateDays(calculateDateRequest.getFromDate(), calculateDateRequest.getToDate());
    }
}
