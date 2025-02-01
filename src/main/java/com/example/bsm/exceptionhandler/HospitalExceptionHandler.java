package com.example.bsm.exceptionhandler;

import com.example.bsm.exception.HospitalNotFoundException;
import com.example.bsm.utility.ErrorStructure;
import com.example.bsm.utility.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@AllArgsConstructor
public class HospitalExceptionHandler {

    private final RestResponseBuilder responseBuilder;

    @ExceptionHandler(HospitalNotFoundException.class)
    public<T> ResponseEntity<ErrorStructure<String>> handleUserNotFoundById(HospitalNotFoundException ex){
        return responseBuilder.error(HttpStatus.NOT_FOUND, ex.getMessage(), "Hospital Not Found By given Id");
    }
}
