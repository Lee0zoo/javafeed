package com.sparta.javafeed.service;

import com.sparta.javafeed.dto.EmailVerifyCheckRequestDto;
import com.sparta.javafeed.dto.SignupRequestDto;
import com.sparta.javafeed.entity.User;
import com.sparta.javafeed.enums.ErrorType;
import com.sparta.javafeed.exception.CustomException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EmailServiceTest {

    @Mock
    private JavaMailSender mailSender;

    @Mock
    UserService userService;

    final static String authNum = "aBdCdFgE";

    @Test
    @DisplayName("메일 인증번호 확인-일치")
    void test1() {
        // given, when
        EmailVerifyCheckRequestDto requestDto = new EmailVerifyCheckRequestDto("email@email.com", "aBdCdFgE");

        // then
        assertEquals(authNum, requestDto.getAuthNum());
    }

    @Test
    @DisplayName("메일 인증번호 확인-불일치")
    void test2() {
        // given
        EmailVerifyCheckRequestDto requestDto = new EmailVerifyCheckRequestDto("email@email.com", "aBcCdFgE");
        EmailService emailService = new EmailService(mailSender, userService);
        User user = new User();

        // when
        // CustomException으로, ErrorType까지 지정해야 하는데, 그 부분을 어떻게 선택할 수 있는지 궁금합니다.
        Exception exception = assertThrows(CustomException.class, () -> {
            emailService.verifyCode(requestDto, user);
        });

        // then
        assertEquals("잘못된 인증번호 입니다.", exception.getMessage());
    }
}