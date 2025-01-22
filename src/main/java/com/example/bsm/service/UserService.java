package com.example.bsm.service;

import com.example.bsm.entity.User;

public interface UserService {


    public User findUserById(int userId);

    User updateUserById(User user);

    User addUser(User user);
}
