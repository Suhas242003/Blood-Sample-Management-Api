package com.example.bsm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DonationLead {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int leadId;
    @CreationTimestamp
    private LocalDate date;
    @CreationTimestamp
    private LocalTime time;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "donationLead")
    private List<DonationRequest> donationRequestList;
}
