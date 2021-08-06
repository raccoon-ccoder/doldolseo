package com.finalprj.doldolseo.service;

import com.finalprj.doldolseo.dto.ReviewDTO;
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
}
