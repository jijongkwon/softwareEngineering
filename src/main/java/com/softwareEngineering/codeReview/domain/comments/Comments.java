package com.softwareEngineering.codeReview.domain.comments;

import com.softwareEngineering.codeReview.domain.BaseTimeEntity;
import com.softwareEngineering.codeReview.domain.posts.Posts;
import com.softwareEngineering.codeReview.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Comments extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "posts_id")
    private Posts posts;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Comments(Long id, String comment, Posts posts, User user)
    {
        this.id = id;
        this.comment = comment;
        this.posts = posts;
        this.user = user;
    }
}
