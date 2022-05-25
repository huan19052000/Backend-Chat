package com.example.bruce.controller;


import com.example.bruce.model.User;
import com.example.bruce.model.request.LoginRequest;
import com.example.bruce.model.request.RegisterRequest;
import com.example.bruce.model.response.ResponseException;
import com.example.bruce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/users")
    public Object getAllUsers() {
        return this.userService.getAll();
    }


    @PostMapping("/users/login")
    public Object login(@RequestBody LoginRequest loginRequest) throws ResponseException {
        return this.userService.login(loginRequest);
    }

    @PostMapping("users/register")
    public Object register(@RequestBody RegisterRequest request) throws ResponseException {
        return this.userService.register(request);
    }
}
