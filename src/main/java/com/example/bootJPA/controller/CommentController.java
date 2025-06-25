package com.example.bootJPA.controller;

import com.example.bootJPA.dto.BoardDTO;
import com.example.bootJPA.dto.CommentDTO;
import com.example.bootJPA.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RequestMapping("/comment/*")
@RestController
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/post")
    @ResponseBody
    public String post(@RequestBody CommentDTO commentDTO){
        long cno = commentService.post(commentDTO);
        return cno > 0 ? "1" : "0";
    }

    @GetMapping("/list/{bno}/{page}")
    @ResponseBody
    public List<CommentDTO> list(@PathVariable("bno") Long bno,
                                 @PathVariable("page") int page){
        page =(page == 0 ? 0 : page - 1);
        Page<BoardDTO> list = commentService.getList(bno. page);
//        List<CommentDTO> list = commentService.getList(bno);
        return list;
    }
}

@PutMapping("/modify")
@ResponseBody
public String modify(@RequestBody CommentDTO commentDTO){
    Long cno = commentService.modify(commentDTO);
    return cno > 0 ? "1" : "0";
}