package com.sparta.javafeed.repository;

import com.sparta.javafeed.entity.User;
import com.sparta.javafeed.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Component
public interface UserRepository extends JpaRepository <User, Long> {
    Optional<User> findByAccountId(String accountId);

    Optional<User> findByEmail(String email);

    //Optional<User> findByAccountIdAndUserStatus(String username, UserStatus userStatus);
}

