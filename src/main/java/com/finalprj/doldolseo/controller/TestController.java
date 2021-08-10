package com.finalprj.doldolseo.controller;

import com.finalprj.doldolseo.vo.Plan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TestController {

    @RequestMapping("/")
    public String test() throws Exception{
        return "/test";
    }




}
