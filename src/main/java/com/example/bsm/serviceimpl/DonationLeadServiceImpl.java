package com.example.bsm.serviceimpl;

import com.example.bsm.entity.DonationLead;
import com.example.bsm.entity.DonationRequest;
import com.example.bsm.entity.User;
import com.example.bsm.exception.BloodBankNotFoundExceptionById;
import com.example.bsm.repository.DonationLeadRepository;
import com.example.bsm.repository.DonationRequestRepository;
import com.example.bsm.repository.UserRepository;
import com.example.bsm.request.DonationLeadRequest;
import com.example.bsm.response.DonationLeadResponse;
import com.example.bsm.service.DonationLeadService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
@AllArgsConstructor
public class DonationLeadServiceImpl implements DonationLeadService {

    private final DonationLeadRepository donationLeadRepository;
    private final UserRepository userRepository;
    private final DonationRequestRepository donationRequestRepository;
    private DonationLead mapToDonationLead(DonationLeadRequest request, DonationLead donationLead) {

        donationLead.setDate(LocalDate.now());
        donationLead.setTime(LocalTime.now());
        return donationLead;
    }

    private DonationLeadResponse mapToDonationLeadResponse(DonationLead donationLead) {
        return DonationLeadResponse.builder()
                .leadId(donationLead.getLeadId())
                .date(LocalDate.now())
                .time(LocalTime.now())
                .build();
    }

    @Override
    public DonationLeadResponse createDonationLead(DonationLeadRequest donationLeadRequest, int userId, int donationId) {
        DonationLead donationLead = mapToDonationLead(donationLeadRequest,new DonationLead());

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BloodBankNotFoundExceptionById("Fail to find User"));

        DonationRequest donationRequest = donationRequestRepository.findById(donationId)
                .orElseThrow(() -> new BloodBankNotFoundExceptionById("Fail to find DonationRequest"));

        donationLead.setUser(user);

        donationLeadRepository.save(donationLead);

        donationRequest.setDonationLead(donationLead);

        return mapToDonationLeadResponse(donationLead);
    }

    @Override
    public DonationLeadResponse findDonationLeadById(int leadId) {
        DonationLead donationLead  = donationLeadRepository.findById(leadId)
                .orElseThrow(() -> new BloodBankNotFoundExceptionById("Fail to find DonationRequest"));
        return mapToDonationLeadResponse(donationLead);
    }

}
