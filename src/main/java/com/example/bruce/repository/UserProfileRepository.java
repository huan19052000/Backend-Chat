package com.example.bruce.repository;

import com.example.bruce.entity.UserProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfileEntity,Integer> {
    //JpaRepository cần truyền vào 1 entity và dạng của key
    //tất cả các hàm trong interface này sé trả về UserProfileEntity or các kiểu dữ liệu nguyên thuỷ
    @Query(nativeQuery = true, value =
            "select * from user_profile where username = :username limit 1")
    UserProfileEntity findOneByUserName(
            @Param("username") String username
    );

}
