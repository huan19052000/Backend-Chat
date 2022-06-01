package com.example.bruce.controller;

import com.example.bruce.model.request.MakingFriendRequest;
import com.example.bruce.model.response.ResponseException;
import com.example.bruce.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class FriendController {
    @Autowired
    private FriendService friendService;

    @PostMapping("friend/making")
    public Object makeFriend(@RequestBody MakingFriendRequest request) throws ResponseException {
        return friendService.makeFriend( request.getFriendId());
    }

    @PostMapping("/api/friends/accept")
    public Object acceptFriend(
            @RequestParam("friendId") int friendId
    ) throws ResponseException {
        return friendService.acceptFriend(
                friendId
        );
    }

    @GetMapping("/api/friends")
    public Object getFriend(
            @RequestParam(value = "status", required = false) String status
    ){
        return friendService.getAllFriends(status);
    }
}
