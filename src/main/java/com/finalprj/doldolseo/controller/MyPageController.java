package com.finalprj.doldolseo.controller;

import com.finalprj.doldolseo.dto.MemberDTO;
import com.finalprj.doldolseo.dto.PlannerDTO;
import com.finalprj.doldolseo.dto.review.ReviewCommentDTO;
import com.finalprj.doldolseo.dto.review.ReviewDTO;
import com.finalprj.doldolseo.service.MemberService;
import com.finalprj.doldolseo.service.impl.PlanServiceImpl;
import com.finalprj.doldolseo.service.impl.PlannerServiceImpl;
import com.finalprj.doldolseo.service.impl.review.ReviewServiceImpl;
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
import java.util.List;

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
    private PlannerServiceImpl plannerService;

    @Autowired
    private PlanServiceImpl planService;

    @Autowired
    UploadProfileUtil profileUtil;

    @Autowired
    private ReviewServiceImpl reviewService;

    @Autowired
    UploadFileUtil fileUtil;

    // 크루 게시글 추가해야함
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

        Page<ReviewCommentDTO> commentList = service.getReviewCommentListByUser(dto.getId(), pageable);
        PagingUtil commentPagingUtil = new PagingUtil(5, commentList);
        model.addAttribute("c_startBlockPage", commentPagingUtil.startBlockPage);
        model.addAttribute("c_endBlockPage", commentPagingUtil.endBlockPage);
        model.addAttribute("commentList", commentList);

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

        Page<ReviewCommentDTO> commentList = service.getReviewCommentListByUser(dto.getId(), pageable);
        PagingUtil commentPagingUtil = new PagingUtil(5, commentList);
        model.addAttribute("c_startBlockPage", commentPagingUtil.startBlockPage);
        model.addAttribute("c_endBlockPage", commentPagingUtil.endBlockPage);
        model.addAttribute("commentList", commentList);

        return "/mypage/mypageDetail";
    }

    // 후기, 댓글 삭제 구현해야함
    @RequestMapping("/removeMember")
    public String removeMember(MemberDTO dto, Model model,HttpServletRequest request){
        List<PlannerDTO> planners = plannerService.selectPlanners(dto.getId());

        // 사용자 플랜, 플래너 모두 삭제
        for(PlannerDTO plannerDTO : planners){
            planService.deletePlans(plannerDTO.getPlannerNo());
            plannerService.deletePlanner(plannerDTO.getPlannerNo());
        }

        // 사용자 후기게시판 댓글, 글 모두 삭제
        service.deleteCommentListByUser(dto.getId());
        List<ReviewDTO> reviewList = service.getReviewListByMember(dto.getId());
        for(ReviewDTO reviewDTO : reviewList){
            service.deleteCommentListByReviewNo(reviewDTO.getReviewNo());
            reviewService.deleteReview(reviewDTO.getReviewNo());
            fileUtil.deleteImages(reviewDTO.getReviewNo());
        }

        // 사용자 프로필, 계정 삭제
        MemberDTO member = service.selectMember(dto.getId());
        profileUtil.deleteProfile(member);
        int result = service.deleteMember(dto.getId());

        String url ="";

        if(result == 0){
            HttpSession session = request.getSession();
            session.invalidate();
            model.addAttribute("removeResult",0);
            url = "/main";
        }else{
            model.addAttribute("removeResult",1);
            url = "redirect:/mypageD?id=" + dto.getId();
        }

        return url;
    }
}
