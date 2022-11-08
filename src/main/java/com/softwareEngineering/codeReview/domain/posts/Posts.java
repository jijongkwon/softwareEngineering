package com.softwareEngineering.codeReview.domain.posts;

import com.softwareEngineering.codeReview.domain.BaseTimeEntity;
import com.softwareEngineering.codeReview.domain.comments.Comment;
import com.softwareEngineering.codeReview.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor // 파라미터 없는 생성자 생성
@Entity
public class Posts extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @ManyToOne(fetch = FetchType.LAZY) // 지연로딩 실제 user를 사용할 때 쿼리문을 보냄
    @JoinColumn(name = "user_id")
    private User user;

    //CascadeType.REMOVE 로 게시글 삭제시 댓글 역시 같이 삭제
    //FetchType.EAGER 데이터 로딩시 같이 로딩
    @OneToMany(mappedBy = "posts", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("id asc")
    private List<Comment> comments;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int view;

    @Builder
    public Posts(String title, String content, String author, int view, User user){
        this.title = title;
        this.author = author;
        this.content = content;
        this.view = view;
        this.user = user;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
