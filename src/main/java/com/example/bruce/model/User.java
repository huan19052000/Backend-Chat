package com.example.bruce.model;

import com.fasterxml.jackson.annotation.JsonProperty;
public class User {
    @JsonProperty(required = true)
    private String username;
    @JsonProperty(required = true)
    private String password;

    @JsonProperty(required = true)
    private String email;

    @JsonProperty(required = true)
    private String avatar;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
