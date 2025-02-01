package com.example.bsm.controller;

import com.example.bsm.request.DonationRequests;
import com.example.bsm.response.DonationResponse;
import com.example.bsm.service.DonationService;
import com.example.bsm.utility.ResponseStructure;
import com.example.bsm.utility.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@AllArgsConstructor
public class DonationController {

    private final DonationService donationService;
    private final RestResponseBuilder restResponseBuilder;

    @PostMapping("/donations")
    public ResponseEntity<ResponseStructure<DonationResponse>> createDonation(
            @RequestBody DonationRequests donationRequests) {
        DonationResponse donationResponse = donationService.createDonation(donationRequests);
        return restResponseBuilder.success(HttpStatus.CREATED, "Donation Created Successfully", donationResponse);
    }

    @PutMapping("/donations/{donationId}")
    public ResponseEntity<ResponseStructure<DonationResponse>> updateDonation(@PathVariable int donationId, @RequestBody DonationRequests donationRequests) {
        DonationResponse donationResponse = donationService.updateDonation(donationId, donationRequests);
        return restResponseBuilder.success(HttpStatus.OK, "Donation Updated Successfully", donationResponse);
    }

    @GetMapping("/donations-update")
    public ResponseEntity<ResponseStructure<List<DonationResponse>>> getAllDonations() {
        List<DonationResponse> donations = donationService.getAllDonations();
        return restResponseBuilder.success(HttpStatus.OK, "Donations Retrieved Successfully", donations);
    }
}
