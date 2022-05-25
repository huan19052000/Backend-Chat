package com.example.bruce.service;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class ChatService {
    @PostConstruct
    public void init(){
        new ChatSocket().open();
    }
}