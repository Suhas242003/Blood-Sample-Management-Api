package com.example.bsm.security;

import com.example.bsm.entity.User;
import com.example.bsm.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Failed to authenticate user");
        }

        User user1 = user.get();
        String role = String.valueOf(user1.getRole());

        return org.springframework.security.core.userdetails.User.builder()
                .username(user1.getEmail())
                .password(user1.getPassword())
                .authorities(role)

                .build();
    }
}
