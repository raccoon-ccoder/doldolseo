package com.finalprj.doldolseo.controller.review;

import com.finalprj.doldolseo.dto.review.ReviewDTO;
import com.finalprj.doldolseo.service.impl.review.ReviewServiceImpl;
import com.finalprj.doldolseo.util.PagingUtil;
import com.finalprj.doldolseo.util.UploadFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

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

    @Autowired
    UploadFileUtil fileUtil;

    //후기게시글 목록
    @GetMapping("/review")
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

    //후기게시글 상세
    @GetMapping(value = "/review/{reviewNo}")
    public String getReview(Model model,
                            @PathVariable("reviewNo") Long reviewNo) {
        ReviewDTO review = service.getReview(reviewNo);
        String content = review.getContent();
        if (content != null) {
            review.setContent(content.replace("temp", "" + review.getReviewNo()));
        }
        model.addAttribute("review", review);
        return "/review/reviewDetail";
    }

    //후기 게시글 등록
    @PostMapping(value = "/review")
    @ResponseBody
    public void insertReview(@RequestParam(required = false) String[] uploadImgs,
                             @RequestBody(required = false) MultipartFile courseImgFile,
                             ReviewDTO dto) {
        if (uploadImgs != null) {
            //String배열 문자열 치환 후 문자열로 변경
            String uploadImg = Arrays.stream(uploadImgs).map(s -> s = s.split("temp")[1].substring(1)).collect(Collectors.joining(","));
            dto.setUploadImg(uploadImg);
        }
        if (courseImgFile != null) {
            dto.setCourseImg(courseImgFile.getOriginalFilename());
        }


        ReviewDTO review = service.insertReview(dto);
        if (review.getUploadImg() != null) {
            fileUtil.moveImages(review.getReviewNo(), review.getUploadImg());
        }

        try {
            if (courseImgFile != null) {
                fileUtil.courseImgSave(review.getReviewNo(), courseImgFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //후기게시글 등록 폼
    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/review/new")
    public String getInsertForm() {
        return "/review/reviewInsert";
    }



    //후기게시글 삭제
    //@PreAuthorize("isAuthenticated() and #dto.id == principal.username")
    // dto 객체 매개변수로 받아와야 할듯
    @DeleteMapping(value = "/review/{reviewNo}")
    public String deleteReview(@PathVariable("reviewNo") Long reviewNo) {
        service.deleteReview(reviewNo);
        fileUtil.deleteImages(reviewNo);
        return "redirect:/review";
    }

    //후기게시글 수정 폼
    //@PreAuthorize("isAuthenticated() and #dto.id == principal.username")
    // dto 객체 매개변수로 받아와야 할듯
    @GetMapping(value = "/review/{reviewNo}/edit")
    public String getUpdateForm(Model model,
                                @PathVariable("reviewNo") Long reviewNo) {
        ReviewDTO dto = service.getReview(reviewNo);
        fileUtil.moveToTemp(reviewNo);
        model.addAttribute("review", dto);

        return "/review/reviewUpdate";
    }

    //후기게시글 수정
    //@PreAuthorize("isAuthenticated() and #dto.id == principal.username")
    @PutMapping(value = "/review/{reviewNo}")
    public String updateReview(@PathVariable("reviewNo") Long reviewNo,
                               ReviewDTO dto,
                               @RequestParam(required = false) String[] uploadImgs) {

        if (uploadImgs != null) {
            //String배열 문자열 치환 후 문자열로 변경
            dto.setUploadImg(dto.getUploadImg() + "," + Arrays.stream(uploadImgs).map(s -> s = s.split("temp")[1].substring(1)).collect(Collectors.joining(",")));
        }

        service.updateReview(reviewNo, dto);
        fileUtil.moveImages(reviewNo, dto.getUploadImg());

        return "redirect:/review";
    }

}
