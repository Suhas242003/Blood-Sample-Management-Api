package com.example.bsm.security;

import com.example.bsm.entity.Admin;
import com.example.bsm.entity.User;
import com.example.bsm.exception.UserNotFoundExceptionById;
import com.example.bsm.repository.AdminRepository;
import com.example.bsm.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AuthUtil {

    private UserRepository userRepository;
    private AdminRepository adminRepository;
    public String getCurrentUserName(){

        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
    public User getCurrentUser(){
      // lambda expression of return the user
        return userRepository.findByEmail(this.getCurrentUserName())
                .orElseThrow(()->new UserNotFoundExceptionById("Failed to authentication"));

    }
    public String getCurrentAdminName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public Admin getCurrentAdmin() {
        return  adminRepository.findByUserEmail(this.getCurrentAdminName())
                .orElseThrow(() -> new UserNotFoundExceptionById("Failed to authenticate"));
    }
}
