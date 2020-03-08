package com.uno.homeloans.web.controller;

import org.apache.http.util.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {



    @ExceptionHandler(Exception.class)
    @ResponseBody
    public  ResponseEntity handleGeneralError(Exception e) {

        ResponseEntity output = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        return output;
    }
}
