package com.example.bsm.exception;

public class BloodBankNotFoundException extends RuntimeException {
    public BloodBankNotFoundException(String message) {
        super(message);
    }
}
