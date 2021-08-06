package com.finalprj.doldolseo.controller;

import com.finalprj.doldolseo.service.ReviewService;
import com.finalprj.doldolseo.util.PagingUtil;
import com.finalprj.doldolseo.vo.ReviewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReviewController {

    @Autowired
    private ReviewService service;

    @RequestMapping("/reviewL")
    public String reviewList(Model model,
                             @RequestParam(name = "areaNo", required = false) Integer areaNo,
                             @PageableDefault(size = 30, sort = "wDate", direction = Sort.Direction.DESC) Pageable pageable) throws Exception {

        Page<ReviewVO> reviewList = null;

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
