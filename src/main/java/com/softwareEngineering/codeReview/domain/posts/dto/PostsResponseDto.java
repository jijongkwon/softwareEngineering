package com.softwareEngineering.codeReview.domain.posts.dto;

import com.softwareEngineering.codeReview.domain.comments.dto.CommentResponseDto;
import com.softwareEngineering.codeReview.domain.posts.Posts;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PostsResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;
    private LocalDate createdDate;
    private LocalDate modifiedDate;
    private Long user_id;
    private List<CommentResponseDto> comments;

    public PostsResponseDto(Posts posts){
        this.id = posts.getId();
        this.title = posts.getTitle();
        this.content = posts.getContent();
        this.author =posts.getAuthor();
        this.createdDate = posts.getCreatedDate();
        this.modifiedDate = posts.getModifiedDate();
        this.user_id = posts.getUser().getId();
        //stream 람다식으로 내부 반복 사용
        this.comments = posts.getComments().stream().map(CommentResponseDto::new).collect(Collectors.toList());
    }
}
