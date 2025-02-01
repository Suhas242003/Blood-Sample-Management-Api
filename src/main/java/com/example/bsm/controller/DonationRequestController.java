package com.example.bsm.controller;

import com.example.bsm.repository.DonationRequestRepository;
import com.example.bsm.request.DonationRequests;
import com.example.bsm.response.DonationRequestResponse;
import com.example.bsm.service.DonationRequestService;
import com.example.bsm.utility.ResponseStructure;
import com.example.bsm.utility.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@AllArgsConstructor
public class DonationRequestController {
    private final DonationRequestRepository donationRequestRepository;
    private final RestResponseBuilder restResponseBuilder;
    private final DonationRequestService donationService;

    @PostMapping("/donation-hospital/{hospitalId}")
    public ResponseEntity<ResponseStructure<DonationRequestResponse>> registerHospitalDonationRequest(@RequestBody DonationRequests donationRequests, @PathVariable int hospitalId){
        DonationRequestResponse donationResponse = donationService.registerHospitalDonationRequest(donationRequests, hospitalId);
        return restResponseBuilder.success(HttpStatus.CREATED, "Hospital Admin Created", donationResponse);
    }
    @PostMapping("/donation-blood/{bankId}")
    public ResponseEntity<ResponseStructure<DonationRequestResponse>> registerBloodBankDonationRequest(@RequestBody DonationRequests donationRequests, @PathVariable int bankId){
        DonationRequestResponse donationResponse = donationService.registerBloodBankDonationRequest(donationRequests, bankId);
        return restResponseBuilder.success(HttpStatus.CREATED, "Hospital Admin Created", donationResponse);
    }
}
