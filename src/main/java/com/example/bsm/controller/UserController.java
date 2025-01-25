package com.example.bsm.controller;

import com.example.bsm.request.UserRequest;
import com.example.bsm.response.UserResponse;
import com.example.bsm.service.UserService;
import com.example.bsm.utility.ResponseStructure;
import com.example.bsm.utility.RestResponseBuilder;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.aspectj.bridge.IMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class UserController {
    @Autowired
    private UserService userService;
    private final RestResponseBuilder responseBuilder;

    @PostMapping("/register")
    public ResponseEntity<ResponseStructure<UserResponse>> addUser(@RequestBody @Valid UserRequest userRequest) {
      UserResponse userResponse = userService.addUser(userRequest);
        return responseBuilder.success(HttpStatus.CREATED, "User Created", userResponse);
    }
    @PostMapping("/addUser")
    public ResponseEntity<ResponseStructure<UserResponse>> addUserOfAdmin(@RequestBody UserRequest userRequest){
        UserResponse userResponse3 = userService.addMainUser(userRequest);
        return responseBuilder.success(HttpStatus.CREATED, "User Created",userResponse3);
    }

    @GetMapping(value = "/find-user")
    public ResponseEntity<ResponseStructure<UserResponse>> findUserById(@RequestParam int userId) {
       UserResponse userResponse1 = userService.findUserById(userId);
        return responseBuilder.success(HttpStatus.FOUND, "User Found", userResponse1);
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<ResponseStructure<UserResponse>> updateUserById(@RequestBody UserRequest userRequest ,@PathVariable int userId) {
       UserResponse userResponse2 = userService.updateUserById(userRequest,userId);
        return responseBuilder.success(HttpStatus.OK, "User Updated", userResponse2);
    }

}
