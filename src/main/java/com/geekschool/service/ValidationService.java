package com.geekschool.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
public class ValidationService {

    private Map<String,String> errorsArray;

    public Map<String,String> checkErrors(BindingResult result){
        errorsArray.clear();
        if (result.hasErrors()){

            List<ObjectError> errors = result.getAllErrors();

            for (ObjectError error : errors) {

                String fieldErrors = ((FieldError) error).getField();
                errorsArray.put(fieldErrors,error.getDefaultMessage());
            }

            return errorsArray;
        }
        else {
            errorsArray.put("success","success");
            return errorsArray;
        }
    }
}