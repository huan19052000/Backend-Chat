package com.example.bruce.model;

import com.fasterxml.jackson.annotation.JsonProperty;
public class User {
    @JsonProperty(required = true)
    private int id;
    @JsonProperty(required = true)
    private String username;
    @JsonProperty(required = true)
    private String password;
    @JsonProperty(required = true)
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
