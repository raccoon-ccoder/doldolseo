package com.finalprj.doldolseo.controller.crew;

import com.finalprj.doldolseo.service.impl.crew.CrewBoardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CrewBoardController {

    @Autowired
    CrewBoardServiceImpl service;

    //크루활동게사글 목록
    @GetMapping("/crew/board")
    public String crewBoardList() throws Exception {
        return "/crew/crewBoard/crewBoardList";
    }

    //크루활동게사글 등록 폼
    @GetMapping("/crew/board/new")
    public String crewBoardInsertForm() throws Exception {
        return "/crew/crewBoard/crewBoardList";
    }

    //크루활동게사글 등록
    @PostMapping("/crew/board")
    public String crewBoardInsert() throws Exception {
        return "/crew/crewBoard/crewBoardList";
    }

    //크루활동게사글 상세
    @GetMapping("/crew/board/{postNo}")
    public String crewBoardDetail(@PathVariable("postNo") Long postNo) throws Exception {
        return "/crew/crewBoard/crewBoardList";
    }

    //크루활동게사글 삭제
    @DeleteMapping("/crew/board/{postNo}")
    public void crewBoardDelete(@PathVariable("postNo") Long boardNo) throws Exception {
    }

    //크루활동게사글 수정 폼
    @GetMapping("/crew/board/{postNo}/edit")
    public String crewBoardUpdateForm(@PathVariable("postNo") String boardNo) throws Exception {
        return "/crew/crewBoard/crewBoardUpdate";
    }


    //크루활동게사글 수정
    @PutMapping("/crew/board/{boardNo}/")
    public void crewBoardUpdate(@PathVariable("boardNo") String boardNo) throws Exception {

    }
}


