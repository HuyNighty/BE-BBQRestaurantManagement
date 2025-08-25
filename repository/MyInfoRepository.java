package com.huynguyen.bbqrestaurantmanagement.repository;

import com.huynguyen.bbqrestaurantmanagement.entity.User;
import com.huynguyen.bbqrestaurantmanagement.dto.response.MyInfoSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyInfoRepository extends JpaRepository<User, String> {

    @Query("SELECT new com.huynguyen.bbqrestaurantmanagement.dto.response.MyInfoSource(u, c) " +
            "FROM User u LEFT JOIN u.customer c ON u.userId = c.user.userId")
    List<MyInfoSource> getAllInfo();
}
