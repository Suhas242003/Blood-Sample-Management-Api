package com.example.bsm.request;

import com.example.bsm.entity.BloodBank;
import com.example.bsm.entity.Hospital;
import com.example.bsm.enums.BloodGroup;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DonationRequests {
    private LocalDate date;
    private LocalTime time;
    private List<BloodGroup> bloodGroup;
    private List<String >cities;


    @ManyToOne
    private Hospital hospital;

    @ManyToOne
    private BloodBank bloodBank;
}
