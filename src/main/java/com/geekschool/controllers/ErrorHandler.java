package com.geekschool.controllers;

import com.geekschool.service.ValidationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@AllArgsConstructor
@ControllerAdvice
public class ErrorHandler {

    private ValidationService validationService;

    @ExceptionHandler(BindException.class)
    public ResponseEntity handleValidationException(BindException e) {
        return ResponseEntity.badRequest().body(validationService.constructErrorPayload(e.getBindingResult()));
    }
}
