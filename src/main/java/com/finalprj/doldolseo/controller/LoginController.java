package com.finalprj.doldolseo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping(value="/main")
    public String main(@RequestParam(value="name", defaultValue = "world") String name, Model model){
        model.addAttribute("name", name);
        return "main";
    }

    @RequestMapping("/memberL")
    public String Login() throws Exception{
        return "/member/memberLogin";
    }
}
