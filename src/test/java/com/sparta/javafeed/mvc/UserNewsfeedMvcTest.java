package com.sparta.javafeed.mvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.javafeed.controller.NewsfeedController;
import com.sparta.javafeed.controller.UserController;
import com.sparta.javafeed.dto.NewsfeedRequestDto;
import com.sparta.javafeed.dto.NewsfeedResponseDto;
import com.sparta.javafeed.dto.SignupRequestDto;
import com.sparta.javafeed.entity.Newsfeed;
import com.sparta.javafeed.entity.User;
import com.sparta.javafeed.jwt.JwtUtil;
import com.sparta.javafeed.security.UserDetailsImpl;
import com.sparta.javafeed.service.NewsfeedService;
import com.sparta.javafeed.service.UserService;
import org.apache.catalina.security.SecurityConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(
        controllers = {UserController.class, NewsfeedController.class},
        excludeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.ASSIGNABLE_TYPE,
                        classes = SecurityConfig.class
                )
        }
)
class UserNewsfeedMvcTest {
    private MockMvc mockMvc;
    private Principal mockPrincipal;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    UserService userService;

    @MockBean
    NewsfeedService newsfeedService;

    @MockBean
    JwtUtil jwtUtil;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(springSecurity(new MockSpringSecurityFilter())).build();
    }

    private void mockUserSetup() {
        String accountId = "abcdef12345";
        String password = "abcdef12345!";
        String name = "이름";
        String email = "email@email.com";

        SignupRequestDto requestDto = new SignupRequestDto(accountId, password, name, email);
        User testUser = new User(requestDto, password);
        UserDetailsImpl testUserDetails = new UserDetailsImpl(testUser);
        mockPrincipal = new UsernamePasswordAuthenticationToken(testUserDetails, "", testUserDetails.getAuthorities());
    }

    @Test
    @DisplayName("회원가입 성공")
    void test1() throws Exception {
        // given
        String accountId = "abcdef123456";
        String password = "abcdef12345!";
        String name = "이름";
        String email = "email@email.com";
        SignupRequestDto signupRequest = new SignupRequestDto(accountId, password, name, email);
        String requestBody = objectMapper.writeValueAsString(signupRequest);

        // when
        ResultActions resultActions = mockMvc.perform(post("/users").content(requestBody)
                .contentType(MediaType.APPLICATION_JSON_VALUE));

        // then
        resultActions.andExpect(status().isOk());
    }

    @Test
    @DisplayName("게시글 작성")
    void test2() throws Exception {
        // given
        this.mockUserSetup();
        List<MultipartFile> list = new ArrayList<>();
        NewsfeedRequestDto requestDto = new NewsfeedRequestDto("제목", "내용", list);
        User user = new User();
        String request = objectMapper.writeValueAsString(requestDto);

        // when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/posts").content(request)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .principal(mockPrincipal));

        // then
        resultActions.andExpect(status().isOk());
    }

}
