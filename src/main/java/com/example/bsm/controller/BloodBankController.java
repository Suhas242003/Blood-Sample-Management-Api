//package com.example.bsm.controller;
//
//import com.example.bsm.request.BloodBankRequest;
//import com.example.bsm.response.BloodBankResponse;
//import com.example.bsm.service.BloodBankService;
//import com.example.bsm.utility.ResponseStructure;
//import com.example.bsm.utility.RestResponseBuilder;
//import jakarta.validation.Valid;
//import lombok.AllArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@AllArgsConstructor
//
//public class BloodBankController {
//
//    @Autowired
//    private final BloodBankService bloodBankService;
//
//    private final RestResponseBuilder responseBuilder;
//
//    // Endpoint to add a new blood bank
//    @PostMapping("/add")
//    public ResponseEntity<ResponseStructure<BloodBankResponse>> addBloodBank(@RequestBody @Valid BloodBankRequest bloodBankRequest) {
//        BloodBankResponse bloodBankResponse = bloodBankService.addBloodBank(bloodBankRequest);
//        return responseBuilder.success(HttpStatus.CREATED, "Blood Bank Created", bloodBankResponse);
//    }
//
//    // Endpoint to get blood bank by ID
//    @GetMapping("/bloodbank/{bloodBankId}")
//    public ResponseEntity<ResponseStructure<BloodBankResponse>> getBloodBankById(@PathVariable int bloodBankId) {
//        BloodBankResponse bloodBankResponse = bloodBankService.getBloodBankById(bloodBankId);
//        return responseBuilder.success(HttpStatus.FOUND, "Blood Bank Found", bloodBankResponse);
//    }
//
//    // Endpoint to update blood bank by ID
//    @PutMapping("/bloodbank/{bloodBankId}")
//    public ResponseEntity<ResponseStructure<BloodBankResponse>> updateBloodBankById(
//            @PathVariable int bloodBankId, @RequestBody @Valid BloodBankRequest bloodBankRequest) {
//        BloodBankResponse bloodBankResponse = bloodBankService.updateBloodBank(bloodBankId, bloodBankRequest);
//        return responseBuilder.success(HttpStatus.OK, "Blood Bank Updated", bloodBankResponse);
//    }
//    @GetMapping("/bloodbank")
//    public ResponseEntity<ResponseStructure<List<BloodBankResponse>>> getAllBloodBanks() {
//        List<BloodBankResponse> bloodBankResponses = bloodBankService.findAllBloodBanks();
//        return responseBuilder.success(HttpStatus.OK, "Blood Banks Retrieved Successfully", bloodBankResponses);
//    }
//
//}
