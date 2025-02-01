package com.example.bsm.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
@Data
@AllArgsConstructor
@Builder

public class DonationLeadResponse {
    private int leadId;
    private LocalDate date;
    private LocalTime time;
}
