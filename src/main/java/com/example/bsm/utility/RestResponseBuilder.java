package com.example.bsm.utility;

import com.example.bsm.entity.User;
import com.example.bsm.utility.ErrorStructure;
import com.example.bsm.utility.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import static org.springframework.web.servlet.function.ServerResponse.status;

@Component
public class RestResponseBuilder {

    public <T> ResponseEntity<ResponseStructure<T>> success(HttpStatus status, String message, T data) {
        return ResponseEntity
                .status(status)
                .body(ResponseStructure.<T>builder()
                        .status(status.value())
                        .message(message)
                        .data(data)
                        .build());
    }

    public <T> ResponseEntity<ErrorStructure<T>> error(HttpStatus status, String message, T rootCause) {
        return ResponseEntity
                .status(status)
                .body(ErrorStructure.<T>builder()
                        .status(status.value())
                        .message(message)
                        .rootCause(rootCause)
                        .build());
    }
}
