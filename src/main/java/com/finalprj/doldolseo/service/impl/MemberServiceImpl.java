package com.finalprj.doldolseo.service.impl;

import com.finalprj.doldolseo.domain.review.Review;
import com.finalprj.doldolseo.domain.review.ReviewComment;
import com.finalprj.doldolseo.dto.MemberDTO;
import com.finalprj.doldolseo.domain.Member;
import com.finalprj.doldolseo.dto.review.ReviewCommentDTO;
import com.finalprj.doldolseo.dto.review.ReviewDTO;
import com.finalprj.doldolseo.repository.MemberRepository;
import com.finalprj.doldolseo.repository.review.ReviewCommentRepository;
import com.finalprj.doldolseo.repository.review.ReviewRepository;
import com.finalprj.doldolseo.service.MemberService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/*
 * 멤버 Service 구현 클래스
 *
 * @Author 백정연
 * @Date 2021/08/03
 */

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository repository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ReviewCommentRepository commentRepository;

    @Override
    public MemberDTO join(MemberDTO memberDTO) throws IOException {
        Member member = dtoToEntity(memberDTO);
        Member validUser = repository.save(member);
        MemberDTO dto = entityToDto(validUser);
        return  dto;
    }

    @Override
    public MemberDTO update(MemberDTO memberDTO) throws IOException {
        Member member = dtoToEntity(memberDTO);
        Member validUser = repository.save(member);
        MemberDTO dto = entityToDto(validUser);
        return  dto;
    }

    @Override
    public MemberDTO selectMember(String id) {
        Optional<Member> member = repository.findById(id);
        return member.isPresent()? entityToDto(member.get()) : null;
    }

    @Override
    public Page<ReviewDTO> getReviewListByUser(String id,Pageable pageable) {
        Page<Review> entityPage = reviewRepository.findAllById(id, pageable);
        Page<ReviewDTO> reviewList = modelMapper.map(entityPage,
                new TypeToken<Page<ReviewDTO>>(){}.getType());

        return reviewList;
    }

    @Override
    public Page<ReviewCommentDTO> getReviewCommentListByUser(String id, Pageable pageable) {
        Page<ReviewComment> entityPage = commentRepository.findAllById(id, pageable);
        Page<ReviewCommentDTO> commentList = modelMapper.map(entityPage,
                new TypeToken<Page<ReviewCommentDTO>>(){}.getType());
        return commentList;
    }

    @Override
    public List<ReviewDTO> getReviewListByMember(String id) {
        List<Review> reviews = reviewRepository.findAllById(id);
        List<ReviewDTO> reviewList = modelMapper.map(reviews, new TypeToken<List<ReviewDTO>>(){}.getType());
        return reviewList;
    }

    @Override
    public void deleteCommentListByUser(String id) {
        commentRepository.deleteAllById(id);
    }

    @Override
    public void deleteCommentListByReviewNo(Long reviewNo) {
        commentRepository.deleteAllByReviewNo(reviewNo);
    }

    @Override
    public int deleteMember(String id) {
        repository.deleteById(id);
        Optional<Member> result = repository.findById(id);
        return !(result.isPresent())? 0 : 1;
        // 계정이 삭제되었다면 0, 그렇지 않다면 1
    }

    @Override
    public int checkId(String id) {
        Optional<Member> result = repository.findById(id);
        return result.isPresent()? 0 : 1;
        // 중복된 아이디가 있으면 0, 없다면 1
    }

    @Override
    public int checkNickname(String nickname) {
        Optional<Member> result = repository.findByNickname(nickname);
        return result.isPresent()? 0 : 1;
        // 중복된 닉네임이 있으면 0, 없다면 1
    }
}