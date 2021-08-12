package com.finalprj.doldolseo.service.review;

import com.finalprj.doldolseo.dto.review.ReviewDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/*
 * 후기게시판 Service
 *
 * @Author 김경일
 * @Date 2021/08/06
 */
public interface ReviewService {

    //후기 게시글 전체 조회
    public Page<ReviewDTO> getReviewList(Pageable pageable);

    //지역번호로 후기 게시글 조회
    public Page<ReviewDTO> getReviewListByArea (Integer areaNo, Pageable pageable);

    //후기 게시글 등록
    public ReviewDTO insertReview(ReviewDTO dto);

    //글번호로 해당 게시글 조회
    public ReviewDTO getReview (Long reviewNo);

    //글번호로 해당 게시글 삭제
    public void deleteReview(Long reviewNo);

    //해당 게시글 수정
    public void updateReview(Long reviewNo, ReviewDTO dto);
}
