package com.craftmanship.insurance.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class InsuranceValidationExceptionController {
    @ExceptionHandler(value = InsuranceValidationException.class)
    public ResponseEntity<String> exception(InsuranceValidationException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.PRECONDITION_FAILED);
    }
}