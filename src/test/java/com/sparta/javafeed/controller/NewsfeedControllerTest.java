package com.sparta.javafeed.controller;

import com.sparta.javafeed.service.NewsfeedService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(NewsfeedController.class)
class NewsfeedControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NewsfeedService newsfeedService;

    @Test
    @DisplayName("게시글 작성")
    void test1() {

    }
}