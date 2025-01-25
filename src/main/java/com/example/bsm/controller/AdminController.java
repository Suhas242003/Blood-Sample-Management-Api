package com.example.bsm.controller;

import com.example.bsm.request.UserRequest;
import com.example.bsm.response.UserResponse;
import com.example.bsm.service.AdminService;
import com.example.bsm.service.UserService;
import com.example.bsm.utility.ResponseStructure;
import com.example.bsm.utility.RestResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminController {
@Autowired
private AdminService adminService;
private UserService userService;
private RestResponseBuilder responseBuilder;
    @PostMapping("/add")
    public ResponseEntity<ResponseStructure<UserResponse>> addUser(@RequestBody UserRequest userRequest){
        UserResponse userResponse = userService.addUser(userRequest);
        return responseBuilder .success(HttpStatus.CREATED,"Created",userResponse);
    }
   
        @PutMapping("/user/promote/{userId}")
        public ResponseEntity<ResponseStructure<UserResponse>> promoteUserToAdmin(@PathVariable int userId) {
            UserResponse userResponse = adminService.promoteUserToAdmin(userId);
            return responseBuilder.success(HttpStatus.OK, "User promoted to Admin", userResponse);
        }
    }


