package com.example.bsm.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BloodBank {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bankId;

    private String name;
    private boolean emergencyUnitCount;



}