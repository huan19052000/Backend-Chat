package com.example.bruce.service;

import com.example.bruce.entity.UserProfileEntity;
import com.example.bruce.model.User;
import com.example.bruce.model.request.LoginRequest;
import com.example.bruce.model.request.RegisterRequest;
import com.example.bruce.model.response.LoginResponse;
import com.example.bruce.model.response.ResponseException;
import com.example.bruce.repository.UserProfileRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class UserService {
    @Autowired
    private UserProfileRepository userProfileRepository;
    public Object login(LoginRequest loginRequest) throws ResponseException {
        UserProfileEntity user = this.userProfileRepository.findOneByUserName(loginRequest.getUserName());
        if (user == null) {
            throw new ResponseException("username not exist in DB");
        }
        if ( new BCryptPasswordEncoder().matches(loginRequest.getPassWord(),  user.getPassWord())){
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setToken(getJWT(user));
            loginResponse.setUser(user);
            return loginResponse;
        }
        return "Password invalid";
    }

    private String getJWT(UserProfileEntity user) {
        Claims claims = Jwts.claims().setSubject(user.getUserName());
        claims.setId(user.getId()+"");
        claims.put("username", user.getUserName());
        claims.put("email", user.getEmail());
        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512,
                "123a@").setExpiration(new Date(new Date().getTime() + 60*60*1000L))
                .compact();
    }

    public Object register(RegisterRequest request) throws ResponseException {
        UserProfileEntity user = this.userProfileRepository.findOneByUserName(request.getUsername());
        if (user != null) {
            throw new ResponseException("User name had exist");
        }
        UserProfileEntity entity = new UserProfileEntity();
        entity.setUserName(request.getUsername());
        entity.setEmail(request.getEmail());
        entity.setAvatar(request.getAvatar());
        entity.setFistName(request.getFirstName());
        entity.setLastName(request.getLastName());
        //encode password
        entity.setPassWord(new BCryptPasswordEncoder().encode(request.getPassword()));

        entity = this.userProfileRepository.save(entity);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(getJWT(entity));
        loginResponse.setUser(entity);
        return loginResponse;
    }

    public Object createUser(User user) {
        UserProfileEntity userProfileEntity = new UserProfileEntity();
        userProfileEntity.setUserName(user.getUsername());
        userProfileEntity.setAvatar(user.getAvatar());
        userProfileEntity.setPassWord(user.getPassword());
        userProfileEntity.setEmail(user.getEmail());
        userProfileEntity = this.userProfileRepository.save(userProfileEntity);
        return userProfileEntity;
    }

    public Object getAll() {
        return userProfileRepository.findAll();
    }
}
