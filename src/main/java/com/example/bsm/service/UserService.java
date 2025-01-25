package com.example.bsm.service;

import com.example.bsm.entity.User;
import com.example.bsm.request.UserRequest;
import com.example.bsm.response.UserResponse;

import java.util.Optional;

public interface UserService {


     UserResponse findUserById(int userId);

    UserResponse updateUserById(UserRequest userRequest,int userId);

    UserResponse addUser(UserRequest userRequest);
    UserResponse addMainUser(UserRequest userRequest);

}
