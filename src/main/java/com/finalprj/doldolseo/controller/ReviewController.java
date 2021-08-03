package com.finalprj.doldolseo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReviewController {

    @RequestMapping("/reviewL")
    public String reviewList() throws Exception{
        return "/review/reviewList";
    }

    @RequestMapping("/reviewD")
    public String reviewDetail() throws Exception{
        return "/review/reviewDetail";
    }

    @RequestMapping("/reviewI")
    public String reviewInsert() throws Exception{
        return "/review/reviewInsert";
    }

    @RequestMapping("/reviewU")
    public String reviewUpdate() throws Exception{
        return "/review/reviewUpdate";
    }

}
