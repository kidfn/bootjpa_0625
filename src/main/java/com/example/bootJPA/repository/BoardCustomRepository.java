package com.example.bootJPA.repository;

import com.example.bootJPA.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardCustomRepository {
    Page<Board> searchBoard(String type, String keyword, Pageable pageable);
}
