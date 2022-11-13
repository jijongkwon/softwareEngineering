package com.softwareEngineering.codeReview.service.comments;

import com.softwareEngineering.codeReview.domain.comments.Comments;
import com.softwareEngineering.codeReview.domain.comments.dto.CommentRequestDto;
import com.softwareEngineering.codeReview.domain.posts.Posts;
import com.softwareEngineering.codeReview.domain.user.User;
import com.softwareEngineering.codeReview.repository.CommentRepository;
import com.softwareEngineering.codeReview.repository.PostsRepository;
import com.softwareEngineering.codeReview.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostsRepository postsRepository;

    // Create
    @Transactional
    public Long save(String name, Long id, CommentRequestDto dto){
        User user = userRepository.findByName(name);
        Posts posts = postsRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        dto.setUser(user);
        dto.setPosts(posts);

        return commentRepository.save(dto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, CommentRequestDto dto){
        Comments comments = commentRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));
        comments.update(dto.getComment());
        return id;
    }

    @Transactional
    public void delete(Long id){
        Comments comments = commentRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));
        commentRepository.delete(comments);
    }
}
