package com.softwareEngineering.codeReview.domain.posts.dto;

import com.softwareEngineering.codeReview.domain.posts.Posts;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class PostsListResponseDto {

    private Long id;
    private String title;
    private String author;
    private int view;
    private LocalDate modifiedDate;

    public PostsListResponseDto(Posts entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.modifiedDate = entity.getModifiedDate();
        this.view = entity.getView();
    }
}
