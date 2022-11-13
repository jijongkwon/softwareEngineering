package com.softwareEngineering.codeReview.repository;

import com.softwareEngineering.codeReview.domain.comments.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comments,Long> {
}
