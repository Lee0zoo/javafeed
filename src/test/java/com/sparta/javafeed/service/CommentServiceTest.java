package com.sparta.javafeed.service;

import com.sparta.javafeed.entity.Comment;
import com.sparta.javafeed.entity.Newsfeed;
import com.sparta.javafeed.entity.User;
import com.sparta.javafeed.repository.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {
    @Mock
    CommentRepository commentRepository;

    @Mock
    NewsfeedService newsfeedService;

    @InjectMocks
    CommentService commentService;

    private Comment comment;

    @BeforeEach
    void setUp() {
        comment = new Comment(new User(), new Newsfeed("title", "description", new User()), "내용");
    }

    @Test
    @DisplayName("comment 객체 가져오기")
    void test1() {
        // given
        Long commentId = 1L;

        given(commentRepository.findById(commentId)).willReturn(Optional.of(comment));

        // when
        Comment foundComment = commentService.getComment(commentId);

        // then
        assertNotEquals(foundComment, null);
    }
}