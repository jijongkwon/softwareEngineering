package com.softwareEngineering.codeReview.domain.comments.dto;

import com.softwareEngineering.codeReview.domain.comments.Comments;
import com.softwareEngineering.codeReview.domain.posts.Posts;
import com.softwareEngineering.codeReview.domain.user.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDto {
    private Long id;
    private String comment;
    private User user;
    private Posts posts;

    public Comments toEntity(){
        Comments comments = Comments.builder()
                .id(id)
                .comment(comment)
                .user(user)
                .posts(posts)
                .build();
        return comments;
    }
}
