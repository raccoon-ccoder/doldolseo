package com.finalprj.doldolseo.controller.review;

import com.finalprj.doldolseo.dto.review.ReviewCommentDTO;
import com.finalprj.doldolseo.service.impl.review.ReviewCommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * 후기게시판 댓글 Controller
 *
 * @Author 김경일
 * @Date 2021/08/11
 */
@RestController
public class ReviewCommentController {

    @Autowired
    ReviewCommentServiceImpl service;

    /* 후기게시판 댓글 조회 */
    @GetMapping(value = "/review/{reviewNo}/comment")
    public ResponseEntity<List<ReviewCommentDTO>> getReviewComment(@PathVariable("reviewNo") Long reviewNo) {
        return ResponseEntity.ok(service.getComments(reviewNo));
    }

    /* 후기게시판 댓글 등록 */
    @PostMapping("/review/{reviewNo}/comment")
    public void insertReviewComment(@PathVariable("reviewNo") Long reviewNo, ReviewCommentDTO dto) {
        service.insertComment(dto);
    }

    /* 후기게시판 댓글 삭제 */
    //@PreAuthorize("isAuthenticated() and #dto.id == principal.username")
    // dto 객체 매개변수로 받아와야 할듯
    // 해당 사용자 아니라면 error 발생하므로 alert() 내부 변경 필요
    @DeleteMapping("/review/{reviewNo}/comment/{commentNo}")
    public void deleteReviewComment(@PathVariable("reviewNo") Long reviewNo,
                                    @PathVariable("commentNo") Long commentNo) {
        service.deleteComment(commentNo);
        System.out.println(commentNo + "댓글 삭제 완료");
    }

    /* 후기게시판 댓글 수정 */
    //@PreAuthorize("isAuthenticated() and #dto.id == principal.username")
    // 해당 사용자 아니라면 error 발생하므로 alert() 내부 변경 필요
    @PutMapping("/review/{reviewNo}/comment/{commentNo}")
    public void putReviewComment(@PathVariable("reviewNo") Long reviewNo,
                                 @PathVariable("commentNo") Long commentNo,
                                 ReviewCommentDTO dto) {

        service.updateComment(commentNo, dto);
        System.out.println(commentNo + "번 댓글 수정 완료");
    }

}
