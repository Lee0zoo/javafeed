package com.sparta.javafeed.service;

import com.sparta.javafeed.dto.EmailVerifyCheckRequestDto;
import com.sparta.javafeed.dto.SignupRequestDto;
import com.sparta.javafeed.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class EmailServiceTest {

    @Mock
    private JavaMailSender mailSender;

    @Mock
    UserService userService;

    @InjectMocks
    EmailService emailService;

    final static String authNum = "aBdCdFgE";

    @Test
    @DisplayName("메일 인증번호 확인")
    void test1() {
        // given
        EmailVerifyCheckRequestDto requestDto = new EmailVerifyCheckRequestDto("email@email.com", "aBdCdFgE");
        emailService.setAuthNum(authNum);
        SignupRequestDto signRequestDto = new SignupRequestDto("abcdef12345", "abcdef12345!", "이름", "email@email.com");
        User user = new User(signRequestDto, "abcdef12345!");
        user.setEmailSentAt(LocalDateTime.now());

        // when
        boolean tf = emailService.verifyCode(requestDto, user);

        // then
        assertTrue(tf);
    }
}