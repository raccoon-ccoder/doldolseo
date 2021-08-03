package com.finalprj.doldolseo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AreaController {

    @GetMapping(value="/areaL")
    public String areaList(){
        return "area/areaList";
    }

    @GetMapping(value="/areaD")
    public String areaDetail(){
        return "area/areaDetail";
    }
}
