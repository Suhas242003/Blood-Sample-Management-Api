package com.example.bsm.service;

import com.example.bsm.request.BloodBankRequest;
import com.example.bsm.response.BloodBankResponse;

import java.util.List;

public interface BloodBankService {


    BloodBankResponse addBloodBank(BloodBankRequest request);


    BloodBankResponse getBloodBankById(int id);


    BloodBankResponse updateBloodBank(int bankId, BloodBankRequest bloodBankRequest);


    List<BloodBankResponse> findAllBloodBanks();

    List<BloodBankResponse> findBloodBank();
}
