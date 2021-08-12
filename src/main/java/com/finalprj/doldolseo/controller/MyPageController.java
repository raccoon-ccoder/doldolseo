package com.finalprj.doldolseo.controller;

import com.finalprj.doldolseo.dto.MemberDTO;
import com.finalprj.doldolseo.dto.ReviewDTO;
import com.finalprj.doldolseo.service.MemberService;
import com.finalprj.doldolseo.util.PagingUtil;
import com.finalprj.doldolseo.util.UploadFileUtil;
import com.finalprj.doldolseo.util.UploadProfileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/*
 *  마이 페이지 Controller
 *
 * @Author 백정연
 * @Date 2021/08/03
 */

@Controller
public class MyPageController {

    @Autowired
    private MemberService service;

    @Autowired
    private UploadProfileUtil profileUtil;

    @RequestMapping("/updateMember")
    public String updateMember(@RequestParam(value = "memberimg") MultipartFile file,
                               @PageableDefault(size = 5, sort = "wDate", direction = Sort.Direction.DESC) Pageable pageable,
                               MemberDTO dto, Model model, HttpServletRequest request) throws Exception{
        MemberDTO originUser = service.selectMember(dto.getId());
        MemberDTO changeUser = profileUtil.updateProfile(originUser, dto, file);
        MemberDTO updatedUser = service.update(changeUser);
        HttpSession session = request.getSession();
        session.setAttribute("member", updatedUser);

        Page<ReviewDTO> reviewList = service.getReviewListByUser(dto.getId(), pageable);
        PagingUtil pagingUtil = new PagingUtil(5, reviewList);
        model.addAttribute("startBlockPage", pagingUtil.startBlockPage);
        model.addAttribute("endBlockPage", pagingUtil.endBlockPage);
        model.addAttribute("reviewList", reviewList);

        // 변경 필요
        return "/mypage/mypageDetail";
    }

    @RequestMapping("/mypageD")
    public String mypageDetail(@PageableDefault(size = 5, sort = "wDate", direction = Sort.Direction.DESC) Pageable pageable
                                ,MemberDTO dto, Model model) throws Exception{

        Page<ReviewDTO> reviewList = service.getReviewListByUser(dto.getId(), pageable);
        PagingUtil pagingUtil = new PagingUtil(5, reviewList);
        model.addAttribute("startBlockPage", pagingUtil.startBlockPage);
        model.addAttribute("endBlockPage", pagingUtil.endBlockPage);
        model.addAttribute("reviewList", reviewList);
        return "/mypage/mypageDetail";
    }
}
