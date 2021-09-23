package com.finalprj.doldolseo.controller;

import com.finalprj.doldolseo.dto.MemberDTO;
import com.finalprj.doldolseo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/*
 *  로그인, 로그아웃 Controller
 *
 * @Author 백정연
 * @Date 2021/08/03
 */

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
}
