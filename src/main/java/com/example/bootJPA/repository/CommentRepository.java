package com.example.bootJPA.repository;

import com.example.bootJPA.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/* JpaRepository<entity name, id class> */
public interface CommentRepository extends JpaRepository<Comment, Long> {
}