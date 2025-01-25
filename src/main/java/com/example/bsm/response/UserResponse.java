package com.example.bsm.response;

import com.example.bsm.enums.AdminType;
import com.example.bsm.enums.BloodGroup;
import com.example.bsm.enums.Gender;
import com.example.bsm.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private int userId;
    private String username;
    private BloodGroup bloodGroup;
    private int age;
    private String availableCity;
    private Gender gender;
    private boolean verified;
    private LocalDate lastDonatedAt;
    private UserRole role;

}
