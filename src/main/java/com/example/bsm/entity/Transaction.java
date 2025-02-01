package com.example.bsm.entity;

import com.example.bsm.enums.BloodGroup;
import com.example.bsm.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int transactionId;

    private LocalDate date;

    private LocalDateTime time;

    private int noOfUnits;

    private TransactionType transactionType;

    @ManyToOne
    private User user;

    @ManyToOne
    private Hospital hospital;
    @ManyToOne
    private BloodBank bloodBank;

    public BloodGroup BloodGroup;
}
