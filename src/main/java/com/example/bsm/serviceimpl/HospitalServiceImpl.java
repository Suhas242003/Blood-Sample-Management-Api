package com.example.bsm.serviceimpl;

import com.example.bsm.entity.Admin;
import com.example.bsm.entity.Hospital;
import com.example.bsm.entity.User;
import com.example.bsm.enums.AdminType;
import com.example.bsm.exception.HospitalNotFoundException;
import com.example.bsm.exception.AdminNotFoundException;
import com.example.bsm.repository.AdminRepository;
import com.example.bsm.repository.HospitalRepository;
import com.example.bsm.repository.UserRepository;
import com.example.bsm.request.HospitalRequest;
import com.example.bsm.response.HospitalResponse;
import com.example.bsm.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HospitalServiceImpl implements HospitalService {

    private final UserRepository userRepository;
    private final HospitalRepository hospitalRepository;
    private final AdminRepository adminRepository;

    @Override
    public HospitalResponse addHospital(HospitalRequest request) {
        Hospital hospital = new Hospital();
        hospital.setName(request.getName());

        Hospital savedHospital = hospitalRepository.save(hospital);
        return mapToResponse(savedHospital);
    }

    @Override
    public HospitalResponse getHospitalById(int id) {
        Hospital hospital = hospitalRepository.findById(id)
                .orElseThrow(() -> new HospitalNotFoundException("Hospital not found with ID: " + id));
        return mapToResponse(hospital);
    }

    @Override
    public HospitalResponse updateHospital(int id, HospitalRequest request) {
        Hospital hospital = hospitalRepository.findById(id)
                .orElseThrow(() -> new HospitalNotFoundException("Hospital not found with ID: " + id));

        hospital.setName(request.getName());
        Hospital updatedHospital = hospitalRepository.save(hospital);

        return mapToResponse(updatedHospital);
    }

    @Override
    public HospitalResponse assignAdminToHospital(HospitalRequest hospitalRequest, int adminId) {
        User user = userRepository.findById(adminId)
                .orElseThrow(() -> new AdminNotFoundException("The specified user does not exist or is not an Admin."));

        Admin admin = user.getAdmin();
        if (admin == null || admin.getAdminType() != AdminType.Owner) {
            throw new AdminNotFoundException("The user is not an Admin of type Owner.");
        }

        Hospital hospital = new Hospital();
        hospital.setName(hospitalRequest.getName());
        admin.setHospital(hospital);
        adminRepository.save(admin);

        return mapToResponse(hospital);
    }

    private HospitalResponse mapToResponse(Hospital hospital) {
        return HospitalResponse.builder()
                .hospitalId(hospital.getHospitalId())
                .name(hospital.getName())
                .build();
    }
}
