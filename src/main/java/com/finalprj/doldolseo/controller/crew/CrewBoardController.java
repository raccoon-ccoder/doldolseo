package com.finalprj.doldolseo.controller.crew;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CrewBoardController {

    @RequestMapping("/crewBoardL")
    public String crewBoardList() throws Exception{
        return "/crew/crewBoard/crewBoardList";
    }

    @RequestMapping("/crewBoardD")
    public String crewBoardDetail() throws Exception{
        return "/crew/crewBoard/crewBoardDetail";
    }

    @RequestMapping("/crewBoardI")
    public String crewBoardInsert() throws Exception{
        return "/crew/crewBoard/crewBoardInsert";
    }

    @RequestMapping("/crewBoardU")
    public String crewBoardUpdate() throws Exception{
        return "/crew/crewBoard/crewBoardUpdate";
    }
}
