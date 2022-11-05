package com.softwareEngineering.codeReview.domain.comments.dto;

import com.softwareEngineering.codeReview.domain.comments.Comment;
import com.softwareEngineering.codeReview.domain.posts.Posts;
import com.softwareEngineering.codeReview.domain.user.User;

import java.time.LocalDateTime;

public class CommentRequestDto {
    private Long id;
    private String comment;
    private User user;
    private Posts posts;

    public Comment toEntity(){
        Comment comments = Comment.builder()
                .id(id)
                .comment(comment)
                .user(user)
                .posts(posts)
                .build();
        return comments;
    }
}
