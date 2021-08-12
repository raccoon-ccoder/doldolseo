package com.finalprj.doldolseo.repository.review;

import com.finalprj.doldolseo.domain.review.ReviewComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewCommentRepository extends JpaRepository<ReviewComment, Long> {

    List<ReviewComment> findAllByReviewNo(Long reviewNo);

    ReviewComment findByCommentNo(Long commnetNo);
}
