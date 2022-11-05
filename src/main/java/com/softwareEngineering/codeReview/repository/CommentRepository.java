package com.softwareEngineering.codeReview.repository;

import com.softwareEngineering.codeReview.domain.comments.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
