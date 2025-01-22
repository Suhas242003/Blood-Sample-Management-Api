package com.example.bsm.entity;


import com.example.bsm.enums.BloodGroup;
import com.example.bsm.enums.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

  @Id
  @GeneratedValue
  private int userId;
  private String username;
  private String email;
  private String password;
  private String phoneNumber;

  @Enumerated(EnumType.STRING)
  private BloodGroup BloodGroup;
  private LocalDate lastDonatedAt;
  private int age;

  @Enumerated(EnumType.STRING)
  private Gender Gender;
  private String availableCity;

  private boolean Verified;


}
