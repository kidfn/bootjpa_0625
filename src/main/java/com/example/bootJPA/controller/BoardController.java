package com.example.bootJPA.controller;

import com.example.bootJPA.dto.BoardDTO;
import com.example.bootJPA.dto.BoardFileDTO;
import com.example.bootJPA.dto.FileDTO;
import com.example.bootJPA.handler.FileHandler;
import com.example.bootJPA.handler.PagingHandler;
import com.example.bootJPA.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/board/*")
public class BoardController {
    private final BoardService boardService;
    private final FileHandler fileHandler;

    @GetMapping("/register")
    public void register(){}

    @PostMapping("/register")
    public String register(BoardDTO boardDTO,
                           @RequestParam(name = "files", required = false)
                           MultipartFile[] files) {
        List<FileDTO> fileList = null;
        if(files != null && files[0].getSize() > 0){
            // 파일 핸들러 작업
            fileList = fileHandler.uploadFiles(files);
        }
        Long bno = boardService.insert(new BoardFileDTO(boardDTO, fileList));
        log.info(">>>> insert id >> {}", bno);
        return "redirect:/board/list";
    }

    @GetMapping("/list")
    public void list(Model model,
                     @RequestParam(name = "pageNo", defaultValue = "1", required = false) int pageNo,
                     @RequestParam(name = "type", required = false) String type,
                     @RequestParam(name = "keyword", required = false) String keyword){
        /* Page + search 포함 */
        int page = pageNo-1; // DB에서 0번지부터 시작하기 위한 변수
        Page<BoardDTO> list = boardService.getList(page, type, keyword);
        log.info(">>>> list >> {}", list.getContent());
        PagingHandler<BoardDTO> ph = new PagingHandler(list,pageNo,type,keyword);
        //model.addAttribute("list", list);
        model.addAttribute("ph", ph);
    }

    @GetMapping("/detail")
    public void detail(Model model, @RequestParam("bno") Long bno){
        BoardFileDTO boardFileDTO = boardService.getDetail(bno);
        log.info(">>>> boardFileDTO > {} ", boardFileDTO);
        model.addAttribute("boardFileDTO", boardFileDTO);
    }

    @PostMapping("/update")
    public String modify(BoardDTO boardDTO,
                         RedirectAttributes redirectAttributes,
                         @RequestParam(name = "files", required = false)
                         MultipartFile[] files){
        log.info(">>>> boardDTO >> {}", boardDTO);
        List<FileDTO> fileList = null;
        if(files !=null && files[0].getSize() > 0){
            fileList = fileHandler.uploadFiles(files);
        }
        Long bno = boardService.modify(new BoardFileDTO(boardDTO, fileList));
        redirectAttributes.addAttribute("bno", boardDTO.getBno());
        return "redirect:/board/detail";
    }


    @GetMapping("/remove")
    public String remove(@RequestParam("bno") Long bno){
        log.info(">>>> bno >> {}", bno);
        boardService.remove(bno);
        return "redirect:/board/list";
    }

    @DeleteMapping("/file/{uuid}")
    @ResponseBody
    public String fileRemove(@PathVariable("uuid") String uuid){
        long bno = boardService.fileRemove(uuid);
        return bno > 0 ? "1":"0";
    }

}