package com.example.bootJPA.repository;

import com.example.bootJPA.entity.Board;
import com.example.bootJPA.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/* JpaRepository<entity name, id class> */
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByBno(long bno);
}
