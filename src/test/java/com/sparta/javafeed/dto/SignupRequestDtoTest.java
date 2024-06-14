package com.sparta.javafeed.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SignupRequestDtoTest {
    private static ValidatorFactory factory;
    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("accountId 빈칸 불가능")
    void test1() {
        SignupRequestDto requestDto = new SignupRequestDto("", "password1234!", "이름", "email@email.com");
        Set<ConstraintViolation<SignupRequestDto>> violations = validator.validate(requestDto);

        for(ConstraintViolation<SignupRequestDto> violation : violations) {
            System.out.println(violation.getMessage());
        }
    }

    @Test
    @DisplayName("비밀번호 형태 검증")
    void test2() {
        SignupRequestDto requestDto = new SignupRequestDto("accountId1234", "password1234", "이름", "email@email.com");
        Set<ConstraintViolation<SignupRequestDto>> violations = validator.validate(requestDto);

        for(ConstraintViolation<SignupRequestDto> violation : violations) {
            System.out.println(violation.getMessage());
        }
    }

}