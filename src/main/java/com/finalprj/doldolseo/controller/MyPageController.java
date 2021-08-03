package com.finalprj.doldolseo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyPageController {

    @RequestMapping("/mypageD")
    public String mypageDetail() throws Exception{
        return "/mypage/mypageDetail";
    }
}
