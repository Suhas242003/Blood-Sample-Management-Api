package com.example.bsm.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BloodBankNotFoundExceptionById extends RuntimeException {
    private final String message;
}
