package com.example.bsm.repository;

import com.example.bsm.entity.DonationRequest;
import com.example.bsm.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
    Hospital findByDonationRequest(DonationRequest donationRequest);
}
