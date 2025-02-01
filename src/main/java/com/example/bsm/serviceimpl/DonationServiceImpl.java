package com.example.bsm.serviceimpl;

import com.example.bsm.entity.Donation;
import com.example.bsm.exception.DonationNotFoundException;
import com.example.bsm.repository.DonationRepository;
import com.example.bsm.request.DonationRequests;
import com.example.bsm.response.DonationResponse;
import com.example.bsm.service.DonationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DonationServiceImpl implements DonationService {

    private final DonationRepository donationRepository;

    private Donation mapToDonation(DonationRequests donationRequests, Donation donation) {
        donation.setDate(donationRequests.getDate());
        donation.setTime(donationRequests.getTime());
        return donation;
    }

    private DonationResponse mapToDonationResponse(Donation donation) {
        return DonationResponse.builder()
                .donationId(donation.getDonationId())
                .date(donation.getDate())
                .time(donation.getTime())
                .build();
    }

    @Override
    public DonationResponse createDonation(DonationRequests donationRequests) {
        Donation donation = this.mapToDonation(donationRequests, new Donation());
        donationRepository.save(donation);
        return this.mapToDonationResponse(donation);
    }

    @Override
    public DonationResponse updateDonation(int donationId, DonationRequests donationRequests) {
        Donation donation = donationRepository.findById(donationId)
                .orElseThrow(() -> new DonationNotFoundException("Donation Not Found with ID: " + donationId));

        this.mapToDonation(donationRequests, donation);
        donationRepository.save(donation);
        return this.mapToDonationResponse(donation);
    }

    @Override
    public List<DonationResponse> getAllDonations() {
        return donationRepository.findAll()
                .stream()
                .map(this::mapToDonationResponse)
                .collect(Collectors.toList());
    }
}
