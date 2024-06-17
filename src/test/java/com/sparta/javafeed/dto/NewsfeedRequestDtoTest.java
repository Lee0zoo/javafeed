package com.sparta.javafeed.dto;

import com.sparta.javafeed.util.S3Util;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

class NewsfeedRequestDtoTest {

    private static ValidatorFactory factory;
    private static Validator validator;

    @Mock
    S3Util s3Util;

    @BeforeAll
    public static void setUp() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("파일 5개 이상 업로드 불가")
    void test1() {
        List<MultipartFile> files = List.of();

        for(int i=0; i<7; i++) {
            MultipartFile file = new MockMultipartFile("name", "Hello World", "text/plain", "Hello World".getBytes());
            files = new ArrayList<>();
            files.add(file);
        }

        NewsfeedRequestDto requestDto = new NewsfeedRequestDto("제목","내용", files);
        Set<ConstraintViolation<NewsfeedRequestDto>> violations = validator.validate(requestDto);

        for(ConstraintViolation<NewsfeedRequestDto> violation : violations) {
            System.out.println(violation.getMessage());
        }
    }

}