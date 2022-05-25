package com.example.bruce.controller;

import com.example.bruce.model.request.MakingFriendRequest;
import com.example.bruce.model.response.ResponseException;
import com.example.bruce.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FriendController {
    @Autowired
    private FriendService friendService;

    @PostMapping("friend/making")
    public Object makeFriend(@RequestBody MakingFriendRequest request) throws ResponseException {
        return friendService.makeFriend( request.getFriendId());
    }

}
