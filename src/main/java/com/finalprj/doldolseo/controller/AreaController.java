package com.finalprj.doldolseo.controller;

import com.finalprj.doldolseo.dto.AreaDTO;
import com.finalprj.doldolseo.service.impl.AreaServiceImpl;
import com.finalprj.doldolseo.util.PagingParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
 * 지역게시판 Controller
 *
 * @Author 김경일
 * @Date 2021/08/04
 */

@Controller
public class AreaController {

    @Autowired
    private AreaServiceImpl service;

    //지역게시판 - 시군구 코드및 컨텐츠타입으로 지역게시글 목록 출력
    @GetMapping(value = "/areaL")
    public String areaList(Model model,
                           AreaDTO dto,
                           @PageableDefault(page = 0, size = 12) Pageable pageable) {

        Page<AreaDTO> areaList;

        if (dto.getSearchKeyword() == null) {
            areaList = service.getAreaList(dto, pageable);
        } else {
            areaList = service.getAreaListBySearch(dto, pageable);
        }

        model.addAttribute("sigungu", dto.getSigungu());
        model.addAttribute("contentType", dto.getContentType());
        model.addAttribute("pagingParam", new PagingParam(10 ,areaList));
        model.addAttribute("areaList", areaList);
        return "area/areaList";
    }

    //지역게시판 - 지역명으로 지역정보 상세조회
    @GetMapping(value = "/areaD")
    public String areaDetail(Model model,
                             @RequestParam(value = "name") String name) {

        AreaDTO area = service.getArea(name);
        model.addAttribute("area", area);
        return "area/areaDetail";
    }

    @GetMapping(value = "/areaT")
    public void printArea() {
        System.out.println("test");
    }
}