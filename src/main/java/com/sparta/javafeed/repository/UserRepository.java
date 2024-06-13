package com.sparta.javafeed.repository;

import com.sparta.javafeed.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface UserRepository extends JpaRepository <User, Long> {
    Optional<User> findByAccountId(String accountId);

    Optional<User> findByEmail(String email);
}

