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

class PasswordReqeustDtoTest
{
    private static ValidatorFactory factory;
    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("비밀번호 빈칸 불가능")
    void test1() {
        PasswordReqeustDto requestDto = new PasswordReqeustDto("");
        Set<ConstraintViolation<PasswordReqeustDto>> violations = validator.validate(requestDto);

        for(ConstraintViolation<PasswordReqeustDto> violation : violations) {
            System.out.println(violation.getMessage());
        }
    }

}