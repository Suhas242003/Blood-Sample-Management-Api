package com.example.bsm.serviceimpl;

import com.example.bsm.entity.Admin;
import com.example.bsm.entity.User;
import com.example.bsm.enums.UserRole;
import com.example.bsm.exception.UserNotFoundExceptionById;
import com.example.bsm.repository.AdminRepository;
import com.example.bsm.repository.HospitalRepository;
import com.example.bsm.repository.UserRepository;
import com.example.bsm.response.AdminResponse;
import com.example.bsm.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final HospitalRepository hospitalRepository;


    @Override
    public AdminResponse promoteUserToAdmin(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundExceptionById("User Not Found"));
        user.setRole(UserRole.GUEST_ADMIN);
        userRepository.save(user);

        Admin admin = Admin.builder()
                .user(user)
                .build();
        Admin savedAdmin = adminRepository.save(admin);

        return AdminResponse.builder()
                .adminId(savedAdmin.getAdminId())
                .user(user)
                .build();
    }

}
