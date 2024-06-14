package com.sparta.javafeed.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class NewsfeedRequestDtoTest {

    private static ValidatorFactory factory;
    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

//    @Test
//    @DisplayName("파일 5개 이상 업로드 불가")
//    void test1() {
//        List<MultipartFile> files = new ArrayList<>();
//        NewsfeedRequestDto requestDto = new NewsfeedRequestDto("제목","내용", );
//        Set<ConstraintViolation<UserInfoRequestDto>> violations = validator.validate(requestDto);
//
//        for(ConstraintViolation<UserInfoRequestDto> violation : violations) {
//            System.out.println(violation.getMessage());
//        }
//    }

}