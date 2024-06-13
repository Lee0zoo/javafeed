package com.sparta.javafeed.service;

import com.sparta.javafeed.entity.Newsfeed;
import com.sparta.javafeed.entity.User;
import com.sparta.javafeed.enums.UserStatus;
import com.sparta.javafeed.repository.NewsfeedRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class NewsfeedServiceTest {

    @Mock
    NewsfeedRepository newsfeedRepository;

    @Mock
    FileService fileService;

    @InjectMocks
    private NewsfeedService newsfeedService;

    private Newsfeed newsfeed;

    @BeforeEach
    void setUp() {
        newsfeed = new Newsfeed("title", "description", new User());
    }

    @Test
    @DisplayName("Newsfeed 객체 가져오기")
    void test1() {
        // given
        Long newsfeedId = 1L;

        given(newsfeedRepository.findByIdAndUser_UserStatus(newsfeedId, UserStatus.ACTIVE)).willReturn(Optional.of(newsfeed));

        // when
        Newsfeed foundNewsfeed = newsfeedService.getNewsfeed(newsfeedId);

        // then
        assertNotEquals(foundNewsfeed, null);
    }
}