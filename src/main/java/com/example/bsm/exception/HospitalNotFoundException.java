package com.example.bsm.exception;

public class HospitalNotFoundException extends RuntimeException {
    private final String message;

    public HospitalNotFoundException(String message) {
        this.message = message;
    }

    public String getMessage(String message) {
        return message;
    }
}
