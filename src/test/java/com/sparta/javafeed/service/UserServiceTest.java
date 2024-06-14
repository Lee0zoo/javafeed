package com.sparta.javafeed.service;

import com.sparta.javafeed.dto.SignupRequestDto;
import com.sparta.javafeed.dto.SignupResponseDto;
import com.sparta.javafeed.entity.User;
import com.sparta.javafeed.jwt.JwtUtil;
import com.sparta.javafeed.repository.ProfileRepository;
import com.sparta.javafeed.repository.UserRepository;
import com.sparta.javafeed.util.S3Util;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    UserRepository userRepository;

    @Mock
    ProfileRepository profileRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    JwtUtil jwtUtil;

    @Mock
    S3Util s3Util;

    @InjectMocks
    UserService userService;

    @Test
    @DisplayName("아이디 중복 확인")
    void test1() {
        // given
        SignupRequestDto requestDto = new SignupRequestDto(
                "abcdef12345", "abcdefg1234!",
                "이름", "email@email.com");

        User testUser = new User(requestDto, "abcdefg1234!");

        // when
        assertEquals(testUser.getAccountId(), "abcdef12345");

    }

    @Test
    @DisplayName("이메일 중복 확인")
    void test2() {
        // given
        SignupRequestDto requestDto = new SignupRequestDto(
                "abcdef12345", "abcdefg1234!",
                "이름", "email@email.com");

        User testUser = new User(requestDto, "abcdefg1234!");

        // when
        assertEquals(testUser.getEmail(), "email@email.com");
    }

    @Test
    @DisplayName("회원가입")
    void test3() {
        SignupRequestDto requestDto = new SignupRequestDto(
                "abcd123456", "abcde1234!",
                "이름", "email@email.com");

        // when
        SignupResponseDto responseDto = userService.signupUser(requestDto);

        // then
        assertEquals(responseDto.getEmail(), "email@email.com");

    }
}