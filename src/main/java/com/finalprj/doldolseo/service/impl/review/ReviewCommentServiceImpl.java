package com.finalprj.doldolseo.service.impl.review;

import com.finalprj.doldolseo.domain.review.Review;
import com.finalprj.doldolseo.domain.review.ReviewComment;
import com.finalprj.doldolseo.dto.review.ReviewCommentDTO;
import com.finalprj.doldolseo.repository.review.ReviewCommentRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewCommentServiceImpl {

    @Autowired
    ReviewCommentRepository repository;

    @Autowired
    ModelMapper modelMapper;

    public List<ReviewCommentDTO> getComments(Long reviewNo) {
        List<ReviewComment> commentEntity = repository.findAllByReviewNo(reviewNo);
        List<ReviewCommentDTO> comments = modelMapper.map(commentEntity, new TypeToken<List<ReviewCommentDTO>>() {
        }.getType());

        return comments;
    }

    public ReviewComment insertComment(ReviewCommentDTO dto) {
        dto.setWDate(LocalDateTime.now());
        ReviewComment comment = modelMapper.map(dto, ReviewComment.class);

        return repository.save(comment);
    }

    @Transactional
    public void updateComment(Long commentNo, ReviewCommentDTO dto) {
        ReviewComment comment = repository.findByCommentNo(commentNo);
        comment.setWDate(LocalDateTime.now());
        comment.setContent(dto.getContent());

    }

    public void deleteComment(Long commentNo) {
        repository.deleteById(commentNo);
    }

}
