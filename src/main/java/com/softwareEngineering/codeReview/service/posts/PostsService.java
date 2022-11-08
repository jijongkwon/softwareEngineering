package com.softwareEngineering.codeReview.service.posts;

import com.softwareEngineering.codeReview.domain.posts.dto.PostsListResponseDto;
import com.softwareEngineering.codeReview.domain.posts.dto.PostsResponseDto;
import com.softwareEngineering.codeReview.domain.posts.dto.PostsSaveRequestDto;
import com.softwareEngineering.codeReview.domain.posts.dto.PostsUpdateRequestDto;
import com.softwareEngineering.codeReview.domain.posts.Posts;
import com.softwareEngineering.codeReview.domain.user.User;
import com.softwareEngineering.codeReview.repository.PostsRepository;
import com.softwareEngineering.codeReview.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;
    private final UserRepository userRepository;

    @Transactional
    public int updateView(Long id){
        return postsRepository.updateView(id);
    }
    @Transactional
    public Long save(PostsSaveRequestDto requestDto, String name){
        User user = userRepository.findByName(name);
        requestDto.setUser(user);
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new
                IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new
                IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new
                IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        postsRepository.delete(posts);
    }

}
