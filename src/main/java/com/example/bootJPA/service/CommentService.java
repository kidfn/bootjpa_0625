package com.example.bootJPA.service;

import com.example.bootJPA.dto.CommentDTO;
import com.example.bootJPA.entity.Comment;

import java.util.List;

public interface CommentService {
    default Comment convertDtoToEntity(CommentDTO commentDTO){
        return Comment.builder()
                .cno(commentDTO.getCno())
                .bno(commentDTO.getbno())
                .writer(commentDTO.getWriter())
                .content(commentDTO.getContent())
                .build();
    }

    default CommentDTO converEntityToDto(Comment comment) {
        return null;
    }

    List<CommentDTO> getList(Long bno);

    long post(CommentDTO commentDTO);
}
