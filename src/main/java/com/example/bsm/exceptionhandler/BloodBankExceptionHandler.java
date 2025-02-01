package com.example.bsm.exceptionhandler;

import com.example.bsm.exception.BloodBankNotFoundExceptionById;
import com.example.bsm.utility.ErrorStructure;
import com.example.bsm.utility.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@AllArgsConstructor
public class BloodBankExceptionHandler {
    private final RestResponseBuilder responseBuilder;

    @ExceptionHandler(BloodBankNotFoundExceptionById.class)
    public<T> ResponseEntity<ErrorStructure<String>> handleUserNotFoundById(BloodBankNotFoundExceptionById ex){
        return responseBuilder.error(HttpStatus.NOT_FOUND, ex.getMessage(), "Blood Bank Not Found By given Id");
    }
}
