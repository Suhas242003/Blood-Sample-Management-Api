package com.example.bsm.entity;


import com.example.bsm.enums.AdminType;
import com.example.bsm.enums.BloodGroup;
import com.example.bsm.enums.Gender;
import com.example.bsm.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.repository.cdi.Eager;

import java.time.LocalDate;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

  @Id

  @GeneratedValue(strategy = GenerationType.AUTO)
  private int userId;
  private String username;
  private String email;
  private String password;
  private String phoneNumber;

  @Enumerated(EnumType.STRING)
  private BloodGroup bloodGroup;
  private LocalDate lastDonatedAt;
  private int age;

  @Enumerated(EnumType.STRING)
  private Gender gender;
  private String availableCity;

  private boolean verified;
  private UserRole role;

 @OneToOne(mappedBy = "user")
  private Admin admin;


}
