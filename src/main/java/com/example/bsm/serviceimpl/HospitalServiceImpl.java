package com.example.bsm.serviceimpl;

import com.example.bsm.entity.Admin;
import com.example.bsm.entity.Hospital;
import com.example.bsm.exception.HospitalNotFoundException;
import com.example.bsm.exception.UserNotFoundExceptionById;
import com.example.bsm.repository.AdminRepository;
import com.example.bsm.repository.HospitalRepository;
import com.example.bsm.repository.UserRepository;
import com.example.bsm.request.HospitalRequest;
import com.example.bsm.response.HospitalResponse;
import com.example.bsm.service.HospitalService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class HospitalServiceImpl implements HospitalService {

    private final HospitalRepository hospitalRepository;

    private final UserRepository userRepository;
    private final AdminRepository adminRepository;

    private HospitalResponse mapToHospitalResponse(Hospital hospital) {
        return HospitalResponse.builder()
                .hospitalId(hospital.getHospitalId())
                .name(hospital.getName())
                .build();
    }

    private Hospital mapToHospital(HospitalRequest hospitalRequest, Hospital hospital) {
        hospital.setName(hospitalRequest.getName());
        return hospital;
    }

    @Override
    public HospitalResponse addHospital(HospitalRequest hospitalRequest) {
        Hospital hospital = this.mapToHospital(hospitalRequest, new Hospital());
        hospital = hospitalRepository.save(hospital);
        return this.mapToHospitalResponse(hospital);
    }


    @Override
    public HospitalResponse findHospitalById(int hospitalId) {
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new HospitalNotFoundException("Hospital Not Found"));
        return this.mapToHospitalResponse(hospital);
    }

    @Override
    public HospitalResponse updateHospitalById(int hospitalId, HospitalRequest hospitalRequest) {
        Hospital exHospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new HospitalNotFoundException("Failed to update"));
        Hospital hospital = this.mapToHospital(hospitalRequest, exHospital);

        Hospital updatedHospital = hospitalRepository.save(hospital);
        return  mapToHospitalResponse(updatedHospital);
    }


    @Override
    public HospitalResponse addAdminHospital(HospitalRequest hospitalRequest, int adminId) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(()-> new UserNotFoundExceptionById("Admin Not Found"));
        Hospital hospital = Hospital.builder()
                .name(hospitalRequest.getName())
                .build();

        hospital = hospitalRepository.save(hospital);
        List<Admin> admins = new ArrayList<>();
        admins.add(admin);
        hospital.setAdmin(admins);

        admin.setHospital(hospital);
        adminRepository.save(admin);
        return this.mapToHospitalResponse(hospital);
    }
}
