package com.finalprj.doldolseo.controller;

import com.finalprj.doldolseo.dto.MemberDTO;
import com.finalprj.doldolseo.domain.Member;
import com.finalprj.doldolseo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private LoginService service;

    @GetMapping(value="/main")
    public String main(@RequestParam(value="name", defaultValue = "world") String name, Model model){
        model.addAttribute("name", name);
        return "main";
    }

    @RequestMapping("/memberL")
    public String memberLogin() throws Exception{
        return "/member/memberLogin";
    }

    @RequestMapping("/login")
    public String loginUser(MemberDTO dto, HttpServletRequest request) throws Exception{
        String url = "";
        HttpSession session = request.getSession();
        Member member = service.login(dto);
        if(member != null){
            session.setAttribute("member",member);
            url = "/main";
        }else{
            request.setAttribute("result","false");
            url = "/member/memberLogin";
        }
        return url;
    }

    @RequestMapping("/logout")
    public String logoutUser(HttpServletRequest request) throws Exception{
        HttpSession session = request.getSession();
        session.invalidate();
        request.setAttribute("result","true");
        return "/main";
    }
}
