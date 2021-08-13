package com.finalprj.doldolseo.repository.review;

import com.finalprj.doldolseo.domain.review.ReviewComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ReviewCommentRepository extends JpaRepository<ReviewComment, Long> {

    List<ReviewComment> findAllByReviewNo(Long reviewNo);

    ReviewComment findByCommentNo(Long commnetNo);

    // 사용자 작성 댓글 목록 조회
    // @Author 백정연, @Date 2021/08/12
    Page<ReviewComment> findAllById(String id, Pageable pageable);

    // 사용자 작성 댓글 삭제
    // @Author 백정연, @Date 2021/08/13
    @Transactional
    void deleteAllById(String id);

    // 특정 글의 모든 댓글 삭제
    // @Author 백정연, @Date 2021/08/13
    @Transactional
    void deleteAllByReviewNo(Long reviewNo);
}
