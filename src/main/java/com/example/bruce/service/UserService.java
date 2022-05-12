package com.example.bruce.service;

import com.example.bruce.entity.UserProfileEntity;
import com.example.bruce.model.request.LoginRequest;
import com.example.bruce.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserProfileRepository userProfileRepository;
    public Object login(LoginRequest loginRequest) {
        UserProfileEntity user = this.userProfileRepository.findOneByUserName(loginRequest.getUserName());
        if (user == null) {
            return "username not exist in DB";
        }
        if (loginRequest.getPassWord().equals(user.getPassWord())) {
            return "Login successfully";
        }
        return "Password invalid";
    }
}
