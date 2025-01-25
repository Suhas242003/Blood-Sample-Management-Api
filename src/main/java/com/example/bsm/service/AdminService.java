package com.example.bsm.service;

import com.example.bsm.response.UserResponse;

public interface AdminService {
    UserResponse promoteUserToAdmin(int userId);
}
