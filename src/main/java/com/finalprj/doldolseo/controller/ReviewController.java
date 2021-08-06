package com.finalprj.doldolseo.controller;

import com.finalprj.doldolseo.dto.ReviewDTO;
import com.finalprj.doldolseo.impl.ReviewServiceImpl;
import com.finalprj.doldolseo.util.PagingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
 * 후기게시판 Controller
 *
 * @Author 김경일
 * @Date 2021/08/06
 */

@Controller
public class ReviewController {

    @Autowired
    private ReviewServiceImpl service;

    @RequestMapping("/reviewL")
    public String reviewList(Model model,
                             @RequestParam(name = "areaNo", required = false) Integer areaNo,
                             @PageableDefault(size = 30, sort = "wDate", direction = Sort.Direction.DESC) Pageable pageable) throws Exception {

        Page<ReviewDTO> reviewList = null;

        if (areaNo == null) {
            reviewList = service.getReviewList(pageable);
        } else {
            reviewList = service.getReviewListByArea(areaNo, pageable);
        }

        PagingUtil pagingUtil = new PagingUtil(10, reviewList);

        model.addAttribute("areaNo", areaNo);
        model.addAttribute("startBlockPage", pagingUtil.startBlockPage);
        model.addAttribute("endBlockPage", pagingUtil.endBlockPage);
        model.addAttribute("reviewList", reviewList);
        return "/review/reviewList";
    }

    @RequestMapping("/reviewD")
    public String reviewDetail() throws Exception {
        return "/review/reviewDetail";
    }

    @RequestMapping("/reviewI")
    public String reviewInsert() throws Exception {
        return "/review/reviewInsert";
    }

    @RequestMapping("/reviewU")
    public String reviewUpdate() throws Exception {
        return "/review/reviewUpdate";
    }

}
