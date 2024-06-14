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

class EmailCheckRequestDtoTest {

    private static ValidatorFactory factory;
    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("이메일 형식 검증")
    void test1() {
        EmailCheckRequestDto requestDto = new EmailCheckRequestDto("12324");
        Set<ConstraintViolation<EmailCheckRequestDto>> violations = validator.validate(requestDto);

        for(ConstraintViolation<EmailCheckRequestDto> violation : violations) {
            System.out.println(violation.getMessage());
        }
    }

}