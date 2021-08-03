package com.finalprj.doldolseo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberController {

    @RequestMapping("/memberJ")
    public String memberJoin() throws Exception{
        return "/member/memberJoin";
    }

    @RequestMapping("/memberJR")
    public String memberJoinResult() throws Exception{
        return "/member/memberJoinResult";
    }

    @RequestMapping("/memberP")
    public String memberPolicy() throws Exception{
        return "/member/memberPolicy";
    }

    @RequestMapping("/memberR")
    public String memberRule() throws Exception{
        return "/member/memberRule";
    }
}
