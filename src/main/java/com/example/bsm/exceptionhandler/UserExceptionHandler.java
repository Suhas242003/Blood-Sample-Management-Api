package com.example.bsm.exceptionhandler;

import com.example.bsm.exception.UserNotFoundExceptionById;
import com.example.bsm.utility.ErrorStructure;
import com.example.bsm.utility.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@AllArgsConstructor
public class UserExceptionHandler {

    private final RestResponseBuilder responseBuilder;

    @ExceptionHandler(UserNotFoundExceptionById.class)
    public<T> ResponseEntity<ErrorStructure<String>> handleUserNotFoundById(UserNotFoundExceptionById ex){
        return responseBuilder.error(HttpStatus.NOT_FOUND, ex.getMessage(), "User Not Found By given Id");
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public<T> ResponseEntity<ErrorStructure<String>> handleUserNameNotFound(UsernameNotFoundException ex){
        return responseBuilder.error(HttpStatus.NOT_FOUND, ex.getMessage(), "User Not Found By given email");
    }

}
