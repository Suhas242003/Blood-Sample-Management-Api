package com.example.bsm.exception;

public class UserNotFoundException extends RuntimeException {
    private final String message;

    public UserNotFoundException(String message){
        super();
        this.message=message;
    }

    public String getMessage(String message){
        return message;
    }
}
