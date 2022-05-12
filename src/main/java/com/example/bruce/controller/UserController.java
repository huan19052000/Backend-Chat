package com.example.bruce.controller;


import com.example.bruce.model.User;
import com.example.bruce.model.request.LoginRequest;
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
    private List<User> users = new ArrayList<>();
    @GetMapping("/users")
    public Object getAllUsers() {
        return users;
    }

    @PostMapping("/users")
    public Object creatUser(@RequestBody User user) throws Exception{
        for (User user1 : users) {
            if (user1.getId() == user.getId()) {
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,"");
            }
        }
        users.add(user);
        return user;
    }

    @PostMapping("/users/login")
    public Object login(@RequestBody LoginRequest loginRequest) {
        return this.userService.login(loginRequest);
    }

    @GetMapping("/users/login")
    public Object getUserLogin(@RequestBody LoginRequest loginRequest) {
        return this.userService.login(loginRequest);
    }
}
