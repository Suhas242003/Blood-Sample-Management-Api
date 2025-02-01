package com.example.bsm.entity;

import com.example.bsm.enums.BloodGroup;
import com.example.bsm.enums.OrganizationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
public class DonationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int donationId;
    @CurrentTimestamp
    private LocalDate date;
    @CurrentTimestamp
    private LocalTime time;
    private List<BloodGroup > bloodGroups;
    private List<String> cities;
    private boolean requestCompleted;

@ManyToOne
    private DonationLead donationLead;
@ManyToOne
    private Donation donation;

    private OrganizationType organizationType;


}
