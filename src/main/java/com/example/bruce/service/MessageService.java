package com.example.bruce.service;

import com.example.bruce.repository.MessageEntityRepository;
import com.example.bruce.security.AuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    private MessageEntityRepository messageEntityRepository;

    public Object getMessages(int friendId){
        return messageEntityRepository.getMessages(
                AuthorizationFilter.getCurrentUserId(),
                friendId
        );
    }
}