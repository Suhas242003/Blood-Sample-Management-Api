package com.example.bsm.serviceimpl;

import com.example.bsm.entity.BloodBank;
import com.example.bsm.entity.DonationRequest;
import com.example.bsm.entity.Hospital;
import com.example.bsm.enums.OrganizationType;
import com.example.bsm.exception.BloodBankNotFoundExceptionById;
import com.example.bsm.exception.HospitalNotFoundException;
import com.example.bsm.repository.BloodBankRepository;
import com.example.bsm.repository.DonationRequestRepository;
import com.example.bsm.repository.HospitalRepository;
import com.example.bsm.request.DonationRequests;
import com.example.bsm.response.DonationRequestResponse;
import com.example.bsm.security.AuthUtil;
import com.example.bsm.service.DonationRequestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class DonationRequestImpl implements DonationRequestService {
    private final DonationRequestRepository donationRequestRepository;
    private final AuthUtil authUtil;
    private final HospitalRepository hospitalRepository;
    private final BloodBankRepository bloodBankRepository;


    private DonationRequest maptoDonation(DonationRequests donationRequests, DonationRequest donationRequest) {
        donationRequest.setDate(donationRequests.getDate());
        donationRequest.setTime(donationRequests.getTime());
        donationRequest.setBloodGroups(donationRequests.getBloodGroup());
        donationRequest.setCities(donationRequests.getCities());
        return donationRequest;
    }

    private DonationRequestResponse maptoDonationResponse(DonationRequest donationRequest) {
        return DonationRequestResponse.builder()
                .donationId(donationRequest.getDonationId())
                .date(donationRequest.getDate())
                .time(donationRequest.getTime())
                .bloodGroup(donationRequest.getBloodGroups())


                .build();
    }
    @Override
    public DonationRequestResponse registerHospitalDonationRequest(DonationRequests donationRequests, int hospitalId) {

        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(()-> new HospitalNotFoundException("Hospital Not Found"+hospitalId));

       DonationRequest donationRequest = this.maptoDonation(donationRequests,new DonationRequest());
//       donationRequest.setHospital(hospital);
        donationRequest.setOrganizationType(OrganizationType.HOSPITAL);
        donationRequestRepository.save(donationRequest);
        hospital.setDonationRequest(donationRequest);

       return this.maptoDonationResponse(donationRequest);


    }

    @Override
    public DonationRequestResponse registerBloodBankDonationRequest(DonationRequests donationRequests, int bankId) {
        BloodBank bloodBank = bloodBankRepository.findById(bankId)
                .orElseThrow(()-> new BloodBankNotFoundExceptionById("Blood Bank Not Found"+bankId));

        DonationRequest donationRequest = this.maptoDonation(donationRequests,new DonationRequest());
        donationRequestRepository.save(donationRequest);
        bloodBank.setDonationRequest(donationRequest);
        return this.maptoDonationResponse(donationRequest);
    }
}
