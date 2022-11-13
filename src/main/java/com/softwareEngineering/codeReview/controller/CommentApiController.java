package com.softwareEngineering.codeReview.controller;


import com.softwareEngineering.codeReview.config.auth.LoginUser;
import com.softwareEngineering.codeReview.config.auth.dto.SessionUser;
import com.softwareEngineering.codeReview.domain.comments.dto.CommentRequestDto;
import com.softwareEngineering.codeReview.service.comments.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentApiController {
    private final CommentService commentService;

    @PostMapping("/api/v1/comments/{id}")
    public Long commentSave(@PathVariable Long id, @RequestBody CommentRequestDto dto,
                            @LoginUser SessionUser user){
        return commentService.save(user.getName(),id,dto);
    }

    @PutMapping("/api/v1/comments/{id}")
    public Long commentUpdate(@PathVariable Long id, @RequestBody CommentRequestDto dto){
        return commentService.update(id, dto);
    }

    @DeleteMapping("/api/v1/comments/{id}")
    public Long delete(@PathVariable Long id){
        commentService.delete(id);
        return id;
    }
}
