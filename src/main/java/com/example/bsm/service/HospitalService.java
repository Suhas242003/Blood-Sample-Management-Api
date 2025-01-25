package com.example.bsm.service;

import com.example.bsm.request.HospitalRequest;
import com.example.bsm.response.HospitalResponse;

public interface HospitalService {
    HospitalResponse addHospital(HospitalRequest request);
    HospitalResponse getHospitalById(int id);
    HospitalResponse updateHospital(int id, HospitalRequest request);
    HospitalResponse assignAdminToHospital(HospitalRequest hospitalRequest, int adminId);
}
