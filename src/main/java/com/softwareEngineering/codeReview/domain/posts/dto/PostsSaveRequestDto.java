package com.softwareEngineering.codeReview.domain.posts.dto;

import com.softwareEngineering.codeReview.domain.posts.Posts;
import com.softwareEngineering.codeReview.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;
    private User user;
    @Builder
    public PostsSaveRequestDto(String title, String content, String author, User user){
        this.title = title;
        this.content = content;
        this.author = author;
        this.user = user;
    }

    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .user(user)
                .build();
    }
}
