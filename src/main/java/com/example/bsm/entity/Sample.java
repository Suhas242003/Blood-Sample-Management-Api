package com.example.bsm.entity;


import com.example.bsm.enums.BloodGroup;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
     @Builder
    public class Sample {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int sampleId;

        @Enumerated(EnumType.STRING)
        private BloodGroup bloodGroup;

        private int quantity;

        private boolean availability;

        private int emergencyUnits;

        private int availableUnits;


    }


