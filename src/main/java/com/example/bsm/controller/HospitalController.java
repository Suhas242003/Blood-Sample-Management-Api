package com.example.bsm.controller;

import com.example.bsm.request.HospitalRequest;
import com.example.bsm.response.HospitalResponse;
import com.example.bsm.service.HospitalService;
import com.example.bsm.utility.ResponseStructure;
import com.example.bsm.utility.RestResponseBuilder;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class HospitalController {

    private final HospitalService hospitalService;

    private final RestResponseBuilder responseBuilder;

    @PreAuthorize("hasAnyAuthority('GUEST_ADMIN')")//OWNER
    @PostMapping("/hospitals-admin/{adminId}")
    public ResponseEntity<ResponseStructure<HospitalResponse>> addAdminHospital(@RequestBody HospitalRequest hospitalRequest, @PathVariable int adminId){
        HospitalResponse hospitalResponse = hospitalService.addAdminHospital(hospitalRequest, adminId);
        return responseBuilder.success(HttpStatus.CREATED, "Hospital Admin Created", hospitalResponse);
    }

    @GetMapping("/hospitals/{hospitalId}")
    public ResponseEntity<ResponseStructure<HospitalResponse>> findHospitalById(@PathVariable int hospitalId){
        HospitalResponse hospitalResponse = hospitalService.findHospitalById(hospitalId);
        return responseBuilder.success(HttpStatus.FOUND, "Hospital Found", hospitalResponse);
    }
    @PreAuthorize("hasAuthority('GUEST_ADMIN')")
    @PutMapping("/hospitals/{hospitalId}")
    public ResponseEntity<ResponseStructure<HospitalResponse>> updateHospitalById(@PathVariable int hospitalId, @RequestBody @Valid HospitalRequest hospitalRequest){
        HospitalResponse hospitalResponse = hospitalService.updateHospitalById(hospitalId, hospitalRequest);
        return  responseBuilder.success(HttpStatus.OK, "Hospital Updated", hospitalResponse);
    }


}
