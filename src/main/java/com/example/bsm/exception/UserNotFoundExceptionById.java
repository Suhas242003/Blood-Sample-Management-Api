package com.example.bsm.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserNotFoundExceptionById extends RuntimeException{
    private final String message;

}
