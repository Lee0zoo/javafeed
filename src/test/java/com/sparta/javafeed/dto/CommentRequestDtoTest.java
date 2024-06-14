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

class CommentRequestDtoTest {

    private static ValidatorFactory factory;
    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("20자 이상 불가능")
    void test1() {
        String s = randomString();
        CommentRequestDto requestDto = new CommentRequestDto(s);
        Set<ConstraintViolation<CommentRequestDto>> violations = validator.validate(requestDto);

        for(ConstraintViolation<CommentRequestDto> violation : violations) {
            System.out.println(violation.getMessage());
        }
    }

    public String randomString() {
        String s = "";
        for(int i=0; i<21; i++) {
            s += "a";
        }
        return s;
    }

}