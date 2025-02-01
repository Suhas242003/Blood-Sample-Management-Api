package com.example.bsm.controller;

import com.example.bsm.repository.DonationLeadRepository;
import com.example.bsm.request.DonationLeadRequest;
import com.example.bsm.request.UserRequest;
import com.example.bsm.response.DonationLeadResponse;
import com.example.bsm.response.UserResponse;
import com.example.bsm.service.DonationLeadService;
import com.example.bsm.utility.ResponseStructure;
import com.example.bsm.utility.RestResponseBuilder;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@AllArgsConstructor
@Builder
public class DonationLeadController {

    private DonationLeadService donationLeadService;
  private RestResponseBuilder responseBuilder;

    @PostMapping("/donationLead")
    public ResponseEntity<ResponseStructure<DonationLeadResponse>> createDonationLead(@RequestBody DonationLeadRequest donationLeadRequest, @RequestParam int userId, @RequestParam int donationId){
        DonationLeadResponse donationLeadResponse = donationLeadService.createDonationLead(donationLeadRequest,userId,donationId);
        return responseBuilder.success(HttpStatus.CREATED,"DonationLead Created",donationLeadResponse);
    }

    @PutMapping("/donationLead/{leadId}")
    public ResponseEntity<ResponseStructure<DonationLeadResponse>> findDonationLeadById(@RequestParam int leadId){
        DonationLeadResponse donationLeadResponse = donationLeadService.findDonationLeadById(leadId);
        return responseBuilder.success(HttpStatus.CREATED,"DonationLead Created",donationLeadResponse);
    }

}
