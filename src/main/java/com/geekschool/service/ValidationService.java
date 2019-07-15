package com.geekschool.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
public class ValidationService {


    public Map<String,List<String>> constructErrorPayload(BindingResult result) {

        Map<String, List<String>> errors = new HashMap<>();
        List<ObjectError> fieldErrors = result.getAllErrors();

        for (ObjectError error : fieldErrors) {

            String fieldError = ((FieldError) error).getField();

            if (errors.get(fieldError) != null) {
                List<String> messages = errors.get(fieldError);
                messages.add(error.getDefaultMessage());
                errors.put(fieldError, messages);
            } else {
                List<String> messages = new ArrayList<>();
                messages.add(error.getDefaultMessage());
                errors.put(fieldError, messages);

            }

        }
        return errors;
    }

}