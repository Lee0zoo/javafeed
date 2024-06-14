package com.sparta.javafeed.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class EmailVerifyCheckRequestDtoTest {

    private static ValidatorFactory factory;
    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("이메일 형식 검중, 인증번호 빈칸 불가능")
    void test1() {
        EmailVerifyCheckRequestDto requestDto = new EmailVerifyCheckRequestDto("1234","");
        Set<ConstraintViolation<EmailVerifyCheckRequestDto>> violations = validator.validate(requestDto);

        for(ConstraintViolation<EmailVerifyCheckRequestDto> violation : violations) {
            System.out.println(violation.getMessage());
        }
    }

}