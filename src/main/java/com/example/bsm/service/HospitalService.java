package com.example.bsm.service;

import com.example.bsm.request.HospitalRequest;
import com.example.bsm.response.HospitalResponse;

public interface HospitalService {
    HospitalResponse addHospital(HospitalRequest hospitalRequest);

    HospitalResponse findHospitalById(int hospitalId);

    HospitalResponse updateHospitalById(int hospitalId, HospitalRequest hospitalRequest);

    HospitalResponse addAdminHospital(HospitalRequest hospitalRequest, int adminId);
}
