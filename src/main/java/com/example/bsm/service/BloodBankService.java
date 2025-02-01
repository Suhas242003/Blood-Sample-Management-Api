package com.example.bsm.service;

import com.example.bsm.response.BloodBankResponse;
import com.example.bsm.enums.BloodGroup;
import com.example.bsm.request.BloodBankRequest;
import com.example.bsm.response.BloodBankPageResponse;
import com.example.bsm.response.BloodBankResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BloodBankService {

    BloodBankResponse findBloodBankById(int bankId);

//    List<BloodBankPageResponse> findAllBloodBankByCity(List<String> city, List<BloodGroup> bloodGroup, int page, int size);

    BloodBankResponse updateBloodBankById(int bankId, BloodBankRequest bankRequest);

    BloodBankResponse addAdminBank(BloodBankRequest bankRequest, int adminId);

    Page<BloodBankPageResponse> findAllBloodBankByCity(List<String> city, List<BloodGroup> bloodGroup, int page, int size);
}
