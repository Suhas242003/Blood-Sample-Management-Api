package com.example.bsm.request;

import com.example.bsm.entity.Hospital;
import com.example.bsm.enums.BloodGroup;
import com.example.bsm.enums.TransactionType;
import com.example.bsm.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionRequest {
    private LocalDate date;
    private LocalTime time;
    private int noOfUnits;
    private TransactionType transactionType;
    private BloodGroup bloodGroup;
}
