package com.example.bsm.controller;

import com.example.bsm.response.AdminResponse;
import com.example.bsm.service.AdminService;
import com.example.bsm.utility.ResponseStructure;
import com.example.bsm.utility.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final RestResponseBuilder responseBuilder;

    @PreAuthorize("hasAuthority('OWNER_ADMIN')")
    @PutMapping("/users/{userId}")
    public ResponseEntity<ResponseStructure<AdminResponse>> promoteUserToAdmin(@PathVariable int userId){
        AdminResponse adminResponse = adminService.promoteUserToAdmin(userId);
        return responseBuilder.success(HttpStatus.CREATED, "Admin Created", adminResponse);

    }




}
