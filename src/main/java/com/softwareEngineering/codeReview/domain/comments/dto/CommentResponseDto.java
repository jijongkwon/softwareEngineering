package com.softwareEngineering.codeReview.domain.comments.dto;

import com.softwareEngineering.codeReview.domain.comments.Comment;

import java.time.LocalDateTime;

public class CommentResponseDto {

    Long id;
    private String comment;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private String name;
    private Long post_id;

    public CommentResponseDto(Comment comment){
        this.id = comment.getId();
        this.comment = comment.getComment();
        this.createdDate = comment.getCreatedDate();
        this.modifiedDate = comment.getModifiedDate();
        this.name = comment.getUser().getName();
        this.post_id = comment.getPosts().getId();
    }
}
