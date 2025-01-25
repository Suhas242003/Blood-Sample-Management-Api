package com.example.bsm.repository;

import com.example.bsm.entity.BloodBank;
import org.springframework.data.jpa.repository.JpaRepository;

public  interface BloodBankRepository extends JpaRepository<BloodBank, Integer> {
}
