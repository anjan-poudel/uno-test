package com.uno.homeloans.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


//@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public ResponseEntity handleGeneralError(Throwable e) {

        if (RuntimeException.class.isAssignableFrom(e.getClass())) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        ResponseEntity output = new ResponseEntity("internal server request", HttpStatus.INTERNAL_SERVER_ERROR);
        return output;
    }
}
