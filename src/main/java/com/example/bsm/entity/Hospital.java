package com.example.bsm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int hospitalId;
    private String name;
    private int adminId;
    @OneToMany(mappedBy = "hospital")
    private List<Admin> admin;

    @OneToOne
    private Address address;

    @OneToMany(mappedBy = "hospital")
    private List<Transaction> transaction;

    @ManyToOne
    private DonationRequest donationRequest;
}
