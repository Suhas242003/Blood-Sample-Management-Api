package com.example.bsm.service;

import com.example.bsm.request.DonationLeadRequest;
import com.example.bsm.response.DonationLeadResponse;
import jakarta.validation.Valid;

public interface DonationLeadService {
    DonationLeadResponse createDonationLead(@Valid DonationLeadRequest donationLeadRequest,int userId,int donationId);

    DonationLeadResponse findDonationLeadById(int leadId);


}
