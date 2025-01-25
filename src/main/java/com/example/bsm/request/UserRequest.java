package com.example.bsm.request;

import com.example.bsm.enums.BloodGroup;
import com.example.bsm.enums.Gender;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    @NotNull(message = "username cannot be null")
    @NotBlank(message = "username cannot be blank")
    @Pattern(regexp = "^[a-zA-Z_]$" ,message = "Username can only contain alphabets and special characters (_)")
    private String username;

    @NotNull(message = "username cannot be null")
    @NotBlank(message = "username cannot be blank")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid email type")
    private String email;

    @NotNull(message = "username cannot be null")
    @NotBlank(message = "username cannot be blank")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@_!$%?]).{8,}$\n",
            message = "Password must be at least 8 characters long, include one uppercase letter, one lowercase letter, one digit, and one special character (@ _ ! $ % ?).")
    private String password;

    @NotNull(message = "username cannot be null")
    @NotBlank(message = "username cannot be blank")
    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$\n", message = "Invalid Mobile no")
    private String phoneNumber;

    @Min(18)
    @Max(60)
    private int age;

    @NotNull(message = "username cannot be null")
    @NotBlank(message = "username cannot be blank")
    @Pattern(regexp = "^[A-Za-z\s-]+$",message = "Invalid city name")
    private String availableCity;


    private Gender gender;
    private BloodGroup bloodGroup;

}
