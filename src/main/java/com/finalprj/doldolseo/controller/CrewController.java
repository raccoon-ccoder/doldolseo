package com.finalprj.doldolseo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CrewController {

    @RequestMapping("/crewL")
    public String crewList() throws Exception{
        return "/crew/crewList";
    }

    @RequestMapping("/crewD")
    public String crewDetail() throws Exception{
        return "/crew/crewDetail";
    }

    @RequestMapping("/crewI")
    public String crewInsert() throws Exception{
        return "/crew/crewInsert";
    }

    @RequestMapping("/crewM")
    public String crewManage() throws Exception{
        return "/crew/crewManage";
    }

    @RequestMapping("/crewJ")
    public String popupCrewJoin() throws Exception{
        return "/crew/popup_crewJoin";
    }
}
