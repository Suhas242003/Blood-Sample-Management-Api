package com.example.bsm.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;
@EnableMethodSecurity
@Configuration
@EnableWebSecurity
public class SecurityConfig  {
    @Bean
    PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf->csrf.disable())
                .authorizeHttpRequests(authorize->authorize.requestMatchers("/register")
                        .permitAll()
//                        .requestMatchers("/user/{userId}").hasAuthority("OWNER_ADMIN")
                        .anyRequest()
                        .authenticated())
                .formLogin(Customizer.withDefaults())
                .build();

    }
}

















