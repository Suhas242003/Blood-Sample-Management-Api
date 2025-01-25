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
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class HospitalController {

    private final HospitalService hospitalService;
    private final RestResponseBuilder responseBuilder;

    @PostMapping("/add-hospital")
    public ResponseEntity<ResponseStructure<HospitalResponse>> addHospital(@RequestBody @Valid HospitalRequest hospitalRequest) {
        HospitalResponse hospitalResponse = hospitalService.addHospital(hospitalRequest);
        return responseBuilder.success(HttpStatus.CREATED, "Hospital Created", hospitalResponse);
    }

    @GetMapping(value = "/find-hospital")
    public ResponseEntity<ResponseStructure<HospitalResponse>> getHospitalById(@RequestParam int hospitalId) {
        HospitalResponse hospitalResponse1 = hospitalService.getHospitalById(hospitalId);
        return responseBuilder.success(HttpStatus.FOUND, "User Found", hospitalResponse1);
    }

    @PutMapping("/hospital/{hospitalId}")
    public ResponseEntity<ResponseStructure<HospitalResponse>> updateHospitalById(@PathVariable int hospitalId, @RequestBody HospitalRequest hospitalRequest) {
        HospitalResponse hospitalResponse2 = hospitalService.updateHospital(hospitalId, hospitalRequest);
        return responseBuilder.success(HttpStatus.OK, "User Updated", hospitalResponse2);
    }

    @PostMapping("/add-admin")
    public ResponseEntity<ResponseStructure<HospitalResponse>> addAdminHospital(@RequestBody @Valid HospitalRequest hospitalRequest, @RequestParam int userId) {
        HospitalResponse hospitalResponse3 = hospitalService.assignAdminToHospital(hospitalRequest, userId);
        return responseBuilder.success(HttpStatus.CREATED, "Hospital adding Admin Created", hospitalResponse3);
    }
}
