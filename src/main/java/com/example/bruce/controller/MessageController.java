package com.example.bruce.controller;

import com.example.bruce.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping("/api/messages")
    public Object getMessages(
            @RequestParam("friendId") int friendId
    ) {
        return messageService.getMessages(friendId);
    }
}
