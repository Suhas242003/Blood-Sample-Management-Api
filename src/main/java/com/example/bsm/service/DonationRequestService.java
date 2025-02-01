package com.example.bsm.service;

import com.example.bsm.request.DonationRequests;
import com.example.bsm.response.DonationRequestResponse;

public interface DonationRequestService {
    DonationRequestResponse registerHospitalDonationRequest(DonationRequests donationRequests, int hospitalId);

    DonationRequestResponse registerBloodBankDonationRequest(DonationRequests donationRequests, int bankId);
}
