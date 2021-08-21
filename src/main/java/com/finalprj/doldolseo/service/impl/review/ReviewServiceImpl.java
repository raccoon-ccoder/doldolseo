package com.finalprj.doldolseo.service.impl.review;

import com.finalprj.doldolseo.domain.Member;
import com.finalprj.doldolseo.dto.review.ReviewDTO;
import com.finalprj.doldolseo.repository.MemberRepository;
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
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    ReviewRepository repository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    ModelMapper modelMapper;

    public Page<ReviewDTO> getReviewPage(Integer areaNo, Pageable pageable) {
        return (areaNo == null) ? getReviewPage(pageable) : getReviewPageByArea(areaNo, pageable);
    }

    @Override
    public Page<ReviewDTO> getReviewPage(Pageable pageable) {
        Page<Review> reviewPage = repository.findAll(pageable);
        return entityPageToDtoPage(reviewPage);
    }

    public Page<ReviewDTO> getReviewPageByArea(Integer areaNo, Pageable pageable) {
        Page<Review> reviewPage = repository.findAllByAreaNo(areaNo, pageable);
        return entityPageToDtoPage(reviewPage);
    }

    @Override
    public ReviewDTO getReview(Long reviewNo) {
        Review review = repository.findByReviewNo(reviewNo);
        return entityToDto(review);
    }

    @Transactional
    public void changeContentImgSrc(Review review) {
        String content = review.getContent();
        if (content != null) {
            review.setContent(content.replace("temp", "" + review.getReviewNo()));
        }
    }

    public ReviewDTO getReviewHitAndChangeContentImgSource(Long reviewNo) {
        Review review = repository.findByReviewNo(reviewNo);
        increaseHit(review);
        changeContentImgSrc(review);
        return entityToDto(review);
    }

    @Transactional
    public void increaseHit(Review review) {
        review.setHit(review.getHit() + 1);
    }

    public ReviewDTO getDTOfilledValues(String[] uploadImgs, MultipartFile courseImgFile, ReviewDTO dto) {
        dto = getDtoWithUploadImgName(uploadImgs, dto);
        dto = getDtoWithCourseImgName(courseImgFile, dto);
        dto = getDtoWithMember(dto);
        dto.setHit(1);
        dto.setWDate(LocalDateTime.now());
        return dto;
    }

    public ReviewDTO getDtoWithUploadImgName(String[] uploadImgs, ReviewDTO dto) {
        if (uploadImgs != null) {
            String uploadImg = Arrays.stream(uploadImgs).map(s -> s = s.split("temp")[1].substring(1)).collect(Collectors.joining(","));
            dto.setUploadImgNames(uploadImg);
        }
        return dto;
    }

    public ReviewDTO getDtoWithCourseImgName(MultipartFile courseImgFile, ReviewDTO dto) {
        if (courseImgFile != null) {
            dto.setCourseImgName(courseImgFile.getOriginalFilename());
        }
        return dto;
    }

    public ReviewDTO getDtoWithMember(ReviewDTO dto) {
        Member member = memberRepository.findOneById(dto.getMember().getId());
        dto.setMember(member);
        return dto;
    }

    @Override
    public ReviewDTO insertReview(ReviewDTO dto) {
        Review review = repository.save(dtoToEntity(dto));
        return entityToDto(review);
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
        review.setUploadImgNames(dto.getUploadImgNames());
        review.setAreaNo(dto.getAreaNo());
    }

    public Review dtoToEntity(ReviewDTO dto) {
        return modelMapper.map(dto, Review.class);
    }

    public ReviewDTO entityToDto(Review review) {
        return modelMapper.map(review, ReviewDTO.class);
    }

    public Page<ReviewDTO> entityPageToDtoPage(Page<Review> reviewPage) {
        return modelMapper.map(reviewPage, new TypeToken<Page<ReviewDTO>>() {
        }.getType());
    }
}
