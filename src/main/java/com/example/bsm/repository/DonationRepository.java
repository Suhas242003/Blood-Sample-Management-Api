package com.example.bsm.repository;

import com.example.bsm.entity.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationRepository  extends JpaRepository<Donation,Integer> {
}
