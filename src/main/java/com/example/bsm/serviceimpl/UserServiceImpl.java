package com.example.bsm.serviceimpl;

import com.example.bsm.exception.UserNotFoundException;
import com.example.bsm.entity.User;
import com.example.bsm.repository.UserRepository;
import com.example.bsm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findUserById(int userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Failed find user"));
    }

    @Override
    public User updateUserById(User user){
        Optional<User> optional = userRepository.findById(user.getUserId());

        if(optional.isPresent()) {
            User exUser = optional.get();
            exUser.setUsername(user.getUsername());
            exUser.setEmail(user.getEmail());
            exUser.setPassword(user.getPassword());
            exUser.setPhoneNumber(user.getPhoneNumber());
            exUser.setBloodGroup(user.getBloodGroup());
            exUser.setLastDonatedAt(user.getLastDonatedAt());
            exUser.setAge(user.getAge());
            exUser.setGender(user.getGender());
            exUser.setAvailableCity(user.getAvailableCity());
            exUser.setVerified(user.isVerified());
            return userRepository.save(exUser);
        }else {
            throw new UserNotFoundException("Failed to update User");
        }

 }


}
