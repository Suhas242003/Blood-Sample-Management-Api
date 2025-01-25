package com.example.bsm.serviceimpl;

import com.example.bsm.entity.Admin;
import com.example.bsm.entity.User;
import com.example.bsm.enums.AdminType;
import com.example.bsm.exception.UserNotFoundExceptionById;
import com.example.bsm.repository.AdminRepository;
import com.example.bsm.repository.UserRepository;
import com.example.bsm.request.UserRequest;
import com.example.bsm.response.UserResponse;
import com.example.bsm.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

//    @Autowired
    private UserRepository userRepository;
    private AdminRepository adminRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public UserResponse addUser(UserRequest userRequest) {
        User user = mapToUser(userRequest ,new User());
        user.setRole(user.getRole());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user =userRepository.save(user);
        return mapToUserResponse(user);

    }

    private UserResponse mapToUserResponse(User user) {
        user = userRepository.save(user);
        return UserResponse.builder()
                .username(user.getUsername())
                .userId(user.getUserId())
                .bloodGroup(user.getBloodGroup())
                .age(user.getAge())
                .availableCity(user.getAvailableCity())
                .gender(user.getGender())
                .verified(user.isVerified())
                .lastDonatedAt(user.getLastDonatedAt())
                .build();
    }

    private static User mapToUser(UserRequest userRequest,User user) {

               user .setUsername(userRequest.getUsername());;
                user.setAge(userRequest.getAge());
                user.setEmail(userRequest.getEmail());
                user.setAvailableCity(userRequest.getAvailableCity());
                user.setPhoneNumber(userRequest.getPhoneNumber());
                user.setGender(userRequest.getGender());
                user.setBloodGroup(userRequest.getBloodGroup());
                user.setPassword(userRequest.getPassword());
        return user;
    }

    @Override
    public UserResponse findUserById(int userId) {
        User user= userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundExceptionById("User Not Found"));
        return UserResponse.builder()
                    .username(user.getUsername())
                    .userId(user.getUserId())
                    .bloodGroup(user.getBloodGroup())
                    .age(user.getAge())
                    .availableCity(user.getAvailableCity())
                    .gender(user.getGender())
                    .verified(user.isVerified())
                    .lastDonatedAt(user.getLastDonatedAt())
                    .build();


    }
    @Override
    public UserResponse updateUserById(UserRequest userRequest , int userId) {
  Optional<User> optional = userRepository.findById(userId);
  if (optional.isEmpty())
    throw new UserNotFoundExceptionById("Failed to update the user");

  User user = mapToUser(userRequest,optional.get());
    userRepository.save(user);
    return this.mapToUserResponse(user);
    }
    @Override
    public UserResponse addMainUser(UserRequest userRequest){
      User user = mapToUser(userRequest,new User());
      user = userRepository.save(user);
      Admin admin = Admin.builder()
              .adminType(AdminType.Owner)
              .user(user)
              .build();
        adminRepository.save(admin) ;
        return mapToUserResponse(user);
    }
}
