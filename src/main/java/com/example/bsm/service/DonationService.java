package com.example.bsm.service;

import com.example.bsm.request.DonationRequests;
import com.example.bsm.response.DonationResponse;

import java.util.List;

public interface DonationService {

    DonationResponse createDonation(DonationRequests donationRequests);

    DonationResponse updateDonation(int donationId, DonationRequests donationRequests);

    List<DonationResponse> getAllDonations();
}
