package com.example.bruce.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MakingFriendRequest {
    @JsonProperty(required = true)
    private int userId;
    @JsonProperty(required = true)
    private int friendId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFriendId() {
        return friendId;
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }
}
