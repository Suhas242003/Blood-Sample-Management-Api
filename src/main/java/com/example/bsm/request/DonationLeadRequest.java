package com.example.bsm.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
@Data
@Builder
@AllArgsConstructor
public class DonationLeadRequest {
    private LocalDate date;
    private LocalTime time;
}
