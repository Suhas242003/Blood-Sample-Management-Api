package com.example.bsm.exception;

import com.example.bsm.utility.ErrorStructure;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FieldErrorExceptionHandler extends ResponseEntityExceptionHandler{

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status) {

        List<ObjectError> objectErrors = ex.getAllErrors();
        List<FieldErrorStructure> errors = new ArrayList<>();

        for (ObjectError objectError : objectErrors) {
            FieldError error = (FieldError) objectError; // Safe assumption that all are FieldError

            errors.add(FieldErrorStructure.builder()
                    .field(error.getField())
                    .message(error.getDefaultMessage())
                    .rejectedValue(error.getRejectedValue())
                    .build());
        }

        ErrorStructure<List<FieldErrorStructure>> error = new ErrorStructure<>();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage("Invalid Data Input");
        error.setRootCause(errors);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);
    }
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FieldErrorStructure{
        private String field;
        private Object rejectedValue;
        private String message;

    }
    }

