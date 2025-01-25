package com.example.bsm.serviceimpl;

import com.example.bsm.entity.User;
import com.example.bsm.entity.Admin;
import com.example.bsm.enums.UserRole;
import com.example.bsm.exception.UserNotFoundException;
import com.example.bsm.repository.UserRepository;
import com.example.bsm.repository.AdminRepository;
import com.example.bsm.response.UserResponse;
import com.example.bsm.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final AdminRepository adminRepository;

    @Override
    public UserResponse promoteUserToAdmin(int userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));


        Admin admin = new Admin();
        admin.setUser(user);


        adminRepository.save(admin);


        return mapToUserResponse(user);
    }
    private UserResponse mapToUserResponse(User user) {
        return UserResponse.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .role(UserRole.valueOf("ADMIN"))  // Assuming you return "ADMIN" role here
                .build();
    }
}
