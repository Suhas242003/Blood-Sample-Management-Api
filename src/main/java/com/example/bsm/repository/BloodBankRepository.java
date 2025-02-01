package com.example.bsm.repository;

import com.example.bsm.entity.BloodBank;
import com.example.bsm.entity.DonationRequest;
import com.example.bsm.enums.BloodGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public  interface BloodBankRepository extends JpaRepository<BloodBank, Integer> {
    public List<BloodBank> findByAddress_CityIn(List<String> cities);

    Page<BloodBank> findByAddressCityInAndSamples_BloodGroupIn(List<String> city, List<BloodGroup> bloodGroups, Pageable pageable);
    BloodBank findByDonationRequest(DonationRequest donationRequest);
}
