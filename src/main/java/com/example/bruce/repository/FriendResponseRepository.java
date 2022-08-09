package com.example.bruce.repository;


import com.example.bruce.model.response.FriendResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendResponseRepository extends JpaRepository<FriendResponse, Integer> {
    @Query(
            nativeQuery = true,
            value = "SELECT " +
                    "user_profile.id as friend_id, " +
                    "user_profile.username as username, " +
                    "user_profile.avatar as avatar, " +
                    "user_profile.first_name as first_name, " +
                    "user_profile.last_name as last_name, " +
                    "message.content as last_message, " +
                    "message.type as type_message, " +
                    "friend.updated_at as updated_at " +
                    "FROM friend " +
                    "JOIN user_profile ON " +
                    "(friend.sender_id = :userId AND friend.receiver_id = user_profile.id) OR " +
                    "(friend.receiver_id = :userId AND friend.sender_id = user_profile.id) " +
                    "LEFT JOIN message ON friend.last_message_id = message.id " +
                    "WHERE (friend.sender_id = :userId or friend.receiver_id = :userId) " +
                    "and (:status ='' OR friend.status = :status) " +
                    "ORDER BY friend.updated_at DESC"
    )
    List<FriendResponse> getFriends(
            @Param("userId") int userId,
            @Param("status") String status
    );

    @Query(
            nativeQuery = true,
            value = "SELECT " +
                    "user_profile.id as friend_id " +
                    "FROM friend " +
                    "JOIN user_profile ON " +
                    "(friend.sender_id = :userId AND friend.receiver_id = user_profile.id) OR " +
                    "(friend.receiver_id = :userId AND friend.sender_id = user_profile.id) " +
                    "LEFT JOIN message ON friend.last_message_id = message.id " +
                    "WHERE (friend.sender_id = :userId or friend.receiver_id = :userId) " +
                    "and (:status ='' OR friend.status = :status) " +
                    "ORDER BY friend.updated_at DESC"
    )
    List<Integer> getFriendIds(
            @Param("userId") int userId,
            @Param("status") String status
    );

    @Modifying
    @Query(nativeQuery = true,
            value = "UPDATE friend set last_message_id = :lastMessage where (sender_id = :senderId AND receiver_id = :receiverId) OR " +
                    "(sender_id = :receiverId AND receiver_id = :senderId)")
    void updateLastMessage(
            @Param("senderId") int senderId,
            @Param("receiverId") int receiverId,
            @Param("lastMessage") String lastMessage
    );

}
