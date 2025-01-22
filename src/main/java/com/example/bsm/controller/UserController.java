package com.example.bsm.controller;

import com.example.bsm.entity.User;
import com.example.bsm.service.UserService;
import com.example.bsm.utility.ResponseStructure;
import com.example.bsm.utility.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class UserController {
    @Autowired
    private UserService userService;
    private  final RestResponseBuilder responseBuilder;
    @PostMapping("/register")
    public ResponseEntity<ResponseStructure<User>> addUser( @RequestBody User user) {
    user = userService.addUser(user);
    return responseBuilder.sucess(HttpStatus.CREATED,"User Created",user);

    }

    @GetMapping(value = "/find-user")
    public ResponseEntity<ResponseStructure<User>> findUserById(@RequestParam int userId){
        User user = userService.findUserById(userId);
        return ResponseEntity.status(HttpStatus.FOUND).body(ResponseStructure.create(HttpStatus.FOUND.value(), "User Found", user));
    }

    @PutMapping("/update-user")
    public ResponseEntity<ResponseStructure<User>> updateUserById(@RequestBody User user){
        user=userService.updateUserById(user);
        return ResponseEntity.status(HttpStatus.OK.value()).body(ResponseStructure.create(HttpStatus.OK.value(), "User updated", user));
    }

}
