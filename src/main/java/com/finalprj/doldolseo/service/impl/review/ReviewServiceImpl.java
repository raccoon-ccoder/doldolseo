package com.finalprj.doldolseo.service.impl.review;

import com.finalprj.doldolseo.dto.review.ReviewDTO;
import com.finalprj.doldolseo.repository.review.ReviewRepository;
import com.finalprj.doldolseo.domain.review.Review;
import com.finalprj.doldolseo.service.review.ReviewService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewRepository repository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public Page<ReviewDTO> getReviewList(Pageable pageable) {

        Page<Review> entityPage = repository.findAll(pageable);
        Page<ReviewDTO> reviewList = modelMapper.map(entityPage, new TypeToken<Page<ReviewDTO>>() {
        }.getType());

        return reviewList;
    }

    @Override
    public Page<ReviewDTO> getReviewListByArea(Integer areaNo, Pageable pageable) {

        Page<Review> entityPage = repository.findAllByAreaNo(areaNo, pageable);
        Page<ReviewDTO> reviewList = modelMapper.map(entityPage, new TypeToken<Page<ReviewDTO>>() {
        }.getType());

        return reviewList;
    }

    @Override
    public ReviewDTO insertReview(ReviewDTO dto) {
        dto.setHit(1);
        dto.setWDate(LocalDateTime.now());

        Review reviewEntity = modelMapper.map(dto, Review.class);
        Review review = repository.save(reviewEntity);

        return modelMapper.map(review, ReviewDTO.class);
    }

    @Override
    @Transactional
    public ReviewDTO getReview(Long reviewNo) {
        Review review = repository.findByReviewNo(reviewNo);
        review.setHit(review.getHit()+1); //조회수 1증가

        return modelMapper.map(review, ReviewDTO.class);
    }

    @Override
    public void deleteReview(Long reviewNo) {
        repository.deleteById(reviewNo);
        System.out.println(reviewNo + "번 게시글 삭제");
    }

    @Override
    @Transactional
    public void updateReview(Long reviewNo, ReviewDTO dto) {
        Review review = repository.findByReviewNo(reviewNo);
        review.setTitle(dto.getTitle());
        review.setContent(dto.getContent());
        review.setUploadImg(dto.getUploadImg());
        review.setAreaNo(dto.getAreaNo());
    }

}
