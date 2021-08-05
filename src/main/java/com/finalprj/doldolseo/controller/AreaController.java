package com.finalprj.doldolseo.controller;

import com.finalprj.doldolseo.service.AreaService;
import com.finalprj.doldolseo.util.PagingUtil;
import com.finalprj.doldolseo.vo.AreaVO;
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
    private AreaService service;

    //지역게시판 - 시군구 코드및 컨텐츠타입으로 지역게시글 목록 출력
    @GetMapping(value = "/areaL")
    public String areaList(Model model,
                           @RequestParam(value = "sigungu") Integer sigungu,
                           @RequestParam(value = "contentType", required = false) Integer contentType,
                           @PageableDefault(page = 0, size = 12) Pageable pageable) {
        Page<AreaVO> areaList = service.getAreaList(sigungu,contentType, pageable);

        PagingUtil pagingUtil = new PagingUtil(10,areaList);

        model.addAttribute("sigungu", sigungu);
        model.addAttribute("contentType", contentType);
        model.addAttribute("startBlockPage", pagingUtil.startBlockPage);
        model.addAttribute("endBlockPage", pagingUtil.endBlockPage);
        model.addAttribute("areaList", areaList);
        return "area/areaList";
    }

    //지역게시판 - 지역명으로 지역정보 상세조회
    @GetMapping(value = "/areaD")
    public String areaDetail(Model model, @RequestParam(value = "name") String name) {

        AreaVO area = service.getArea(name);
        model.addAttribute("area",area);
        return "area/areaDetail";
    }

    @GetMapping(value = "/areaT")
    public void printArea() {
        System.out.println("test");
    }
}