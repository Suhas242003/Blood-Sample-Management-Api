package com.example.bsm.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class RestResponseBuilder {

    public <T> ResponseEntity<ResponseStructure<T>> success(HttpStatus status, String message, T data){
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
    public <T> ResponseEntity<PageStructure<T>> success(HttpStatus status, String message, T data,int size,int page,int totalPages) {
        PageStructure<T> structure = new PageStructure<T>();
        structure.setStatus(status.value());
        structure.setMessage(message);
        structure.setData(data);
        structure.setSize(size);
        structure.setPage(page);
        structure.setTotalPages(totalPages);

        return ResponseEntity
                .status(status)
                .body(structure);

    }

}
