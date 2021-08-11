package com.finalprj.doldolseo.controller;

import com.finalprj.doldolseo.dto.MemberDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyPageController {

    @RequestMapping("/updateMember")
    public String updateMember(MemberDTO dto) throws Exception{

        return "/mypage/mypageDetail";
    }

    @RequestMapping("/mypageD")
    public String mypageDetail() throws Exception{
        return "/mypage/mypageDetail";
    }
}
