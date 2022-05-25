package com.example.bruce.model.response;

import com.example.bruce.entity.UserProfileEntity;

public class LoginResponse {
    private String token;
    private UserProfileEntity user;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserProfileEntity getUser() {
        return user;
    }

    public void setUser(UserProfileEntity user) {
        this.user = user;
    }
}
