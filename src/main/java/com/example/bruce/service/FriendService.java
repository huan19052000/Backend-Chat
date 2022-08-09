package com.example.bruce.service;

import com.example.bruce.entity.FriendEntity;
import com.example.bruce.entity.UserProfileEntity;
import com.example.bruce.model.response.ResponseException;
import com.example.bruce.repository.FriendRepository;
import com.example.bruce.repository.FriendResponseRepository;
import com.example.bruce.repository.UserProfileRepository;
import com.example.bruce.security.AuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendService {
    @Autowired
    private UserProfileRepository userProfileRepository;
    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private FriendResponseRepository friendResponseRepository;

    public Object makeFriend(int friendId) throws ResponseException {
        int userId = AuthorizationFilter.getCurrentUserId();
        if (userId == friendId) {
            throw new ResponseException("User id can't equal friend id");
        }
        UserProfileEntity user = userProfileRepository.findById(userId);
        //check xem user co ton tai ko
        if (user == null) {
            throw new ResponseException("User not exist");
        }
        //check friend ton tai
        UserProfileEntity userFriend = this.userProfileRepository.findById(friendId);
        if (userFriend == null) {
            throw new ResponseException("Friend not exist");
        }
        FriendEntity friend = this.friendRepository.findOnbyUserIdAndFriendId(userId, friendId);
        // kiem tra user co ket ban voi friend ko
        if (friend != null) {
            if (!friend.getStatus().equals("canceled")) {
                throw new ResponseException("You and your friend had made friend");
            } else {
                friend.setSenderId(userId);
                friend.setReceiverId(friendId);
                friend.setStatus("pending");
                //update to db
                this.friendRepository.save(friend);
                return friend;
            }
        }

        //kiem tra friend co ket ban voi user ko
        friend = this.friendRepository.findOnbyUserIdAndFriendId(friendId, userId);
        if (friend != null) {
            if (!friend.getStatus().equals("canceled")) {
                throw new ResponseException("You and your friend had made friend");
            } else {
                friend.setSenderId(userId);
                friend.setReceiverId(friendId);
                friend.setStatus("pending");
                //update to db
                this.friendRepository.save(friend);
                return friend;
            }
        }
        friend = new FriendEntity();
        friend.setSenderId(userId);
        friend.setReceiverId(friendId);
        friend.setStatus("pending");
        //update to db
        this.friendRepository.save(friend);
        return friend;
    }

    public Object acceptFriend(int friendId) throws ResponseException {
        int userId = AuthorizationFilter.getCurrentUserId();
        FriendEntity friendEntity = this.friendRepository.findOneBySenderIdAndReceiverId(
                friendId, userId
        );
        if ( friendEntity == null ){
            throw new ResponseException("You can not accepted friend because user "+friendId+" not yet request");
        }
        if ( !"pending".equals(friendEntity.getStatus())){
            throw new ResponseException("Status must be pending");
        }
        friendEntity.setStatus("accepted");
        this.friendRepository.save(friendEntity);
        return friendEntity;

    }
    public Object getAllFriends(String status) {
        return friendResponseRepository.getFriends(
                AuthorizationFilter.getCurrentUserId(), status == null ? "" : status
        );
    }
}
