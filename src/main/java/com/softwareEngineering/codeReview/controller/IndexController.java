package com.softwareEngineering.codeReview.controller;

import com.softwareEngineering.codeReview.config.auth.LoginUser;
import com.softwareEngineering.codeReview.config.auth.dto.SessionUser;
import com.softwareEngineering.codeReview.domain.comments.dto.CommentResponseDto;
import com.softwareEngineering.codeReview.domain.posts.dto.PostsResponseDto;
import com.softwareEngineering.codeReview.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());

        if(user != null){
            System.out.println(user.getName());
            model.addAttribute("user_name", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(Model model, @LoginUser SessionUser user){
        model.addAttribute("user_name", user.getName());
        return "posts-save";
    }

    @GetMapping("/posts/read/{id}")
    public String postRead(@PathVariable Long id, @LoginUser SessionUser user, Model model){
        PostsResponseDto dto = postsService.findById(id);
        List<CommentResponseDto> comments = dto.getComments();

        /* 댓글 관련 */
        if(comments != null && !comments.isEmpty()){
            model.addAttribute("comments", comments);
        }

        if(user != null){
            model.addAttribute("user_name", user.getName());

            /* 게시글 작성자 본인인지 확인 */
            if(dto.getUser_id().equals(user.getId())){
                model.addAttribute("author", true);
            }

            /* 댓글 작성자 본인인지 확인 */
            for (int i = 0; i < comments.size(); i++){
                boolean isAuthor = comments.get(i).getUser_id().equals(user.getId());
                model.addAttribute("isAuthor", isAuthor);
            }
        }

        postsService.updateView(id);
        model.addAttribute("posts",dto);

        return "posts-read";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model, @LoginUser SessionUser user){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("posts", dto);
        model.addAttribute("user_name", user.getName());
        return "posts-update";
    }

}
