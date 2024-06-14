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

class UserInfoRequestDtoTest {

    private static ValidatorFactory factory;
    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("이름 빈칸 불가능")
    void test1() {
        UserInfoRequestDto requestDto = new UserInfoRequestDto("","한 줄 소개");
        Set<ConstraintViolation<UserInfoRequestDto>> violations = validator.validate(requestDto);

        for(ConstraintViolation<UserInfoRequestDto> violation : violations) {
            System.out.println(violation.getMessage());
        }
    }

}