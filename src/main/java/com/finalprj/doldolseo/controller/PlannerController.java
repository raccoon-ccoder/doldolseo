package com.finalprj.doldolseo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PlannerController {

    @RequestMapping("/planD")
    public String planDetail() throws Exception{
        return "/mypage/plan/planDetail";
    }

    @RequestMapping("/planI")
    public String planInsert() throws Exception{
        return "/mypage/plan/planInsert";
    }

    @RequestMapping("/planL")
    public String planList() throws Exception{
        return "/mypage/plan/planList";
    }

    @RequestMapping(value="/plantest.do", method = RequestMethod.POST)
    @ResponseBody
    public void planInsertTest(@RequestParam(value = "date[]") String[] date, @RequestParam(value = "place[]") String[] place, @RequestParam(value = "plan_intro[]") String[] plan_intro, @RequestParam(value = "y[]") String[] y, @RequestParam(value = "x[]") String[] x, @RequestParam(value = "time[]") String[] time){

        for(int num=0;num<date.length;num++){
            System.out.println(date[num] + " " + place[num] + " " + plan_intro[num] + " " + y[num] + " " + x[num] + " " + time[num]);
        }

    }
}
