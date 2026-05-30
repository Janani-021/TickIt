package com.in.janani.service;

import com.in.janani.model.User;
import com.in.janani.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public User createuser(User user){
        return userRepository.save(user);
    }
    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(()-> new RuntimeException("User not found"));
    }
}
