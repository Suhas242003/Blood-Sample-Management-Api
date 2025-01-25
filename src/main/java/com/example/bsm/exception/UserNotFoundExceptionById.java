package com.example.bsm.exception;

public class UserNotFoundExceptionById extends RuntimeException{

    public UserNotFoundExceptionById(String userNotFound) {
    }

    public UserNotFoundExceptionById(int userId) {
    }

    public void UserNameNotFoundException(String email) {
    }
}
