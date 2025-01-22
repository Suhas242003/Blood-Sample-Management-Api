package com.example.bsm.utility;

import com.example.bsm.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

    @RestControllerAdvice
    public class ApplicationExceptionHandler {

        @ExceptionHandler
        public ErrorStructure handleUserNotFoundById(UserNotFoundException ex) {
//    	 Map<String, Object> response = new HashMap<String,Object>();
//    	 response.put("Status", 404);
//    	 response.put("message", ex.getMessage());
//    	 response.put("rootCause", "User not found by the given Id");
//
//    	 return response;

            return ErrorStructure.create(HttpStatus.NOT_FOUND.value(), ex.getMessage(), "Actor Not found by the given Id");
        }
//    @ExceptionHandler
//    public ErrorStructure handleActorNotFoundById(ActorNotFoundException ex){
//        return ErrorStructure.create(HttpStatus.NOT_FOUND.value(), ex.getMessage(), "Actors not Present");
//    }



    }


