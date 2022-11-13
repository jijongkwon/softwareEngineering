package com.softwareEngineering.codeReview.controller;

import com.softwareEngineering.codeReview.config.auth.LoginUser;
import com.softwareEngineering.codeReview.config.auth.dto.SessionUser;
import com.softwareEngineering.codeReview.domain.comments.dto.CommentRequestDto;
import com.softwareEngineering.codeReview.domain.posts.dto.PostsResponseDto;
import com.softwareEngineering.codeReview.domain.posts.dto.PostsSaveRequestDto;
import com.softwareEngineering.codeReview.domain.posts.dto.PostsUpdateRequestDto;
import com.softwareEngineering.codeReview.service.comments.CommentService;
import com.softwareEngineering.codeReview.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;
    private final CommentService commentService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto, @LoginUser SessionUser user){
        return postsService.save(requestDto, user.getName());
    }

    @PostMapping("/api/v1/comments/{id}")
    public Long commentSave(@PathVariable Long id, @RequestBody CommentRequestDto dto,
                            @LoginUser SessionUser user){
        return commentService.save(user.getName(),id,dto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id){
        postsService.delete(id);
        return id;
    }
}
