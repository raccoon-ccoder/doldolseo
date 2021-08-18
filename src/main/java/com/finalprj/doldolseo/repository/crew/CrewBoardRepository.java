package com.finalprj.doldolseo.repository.crew;

import com.finalprj.doldolseo.domain.crew.CrewPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CrewBoardRepository extends JpaRepository<CrewPost, Long> {

    //카테고리로 게시글 검색
    Page<CrewPost> findAllByCategory(String category, Pageable pageable);

    CrewPost findByPostNo(Long postNo);

    // 사용자 id로 게시글 검색 (Page 타입)
    // @Author 백정연, @Date 2021/08/17
    Page<CrewPost> findAllByMemberId(String id, Pageable pageable);

    // 사용자 id로 게시글 검색 (List 타입)
    // @Author 백정연, @Date 2021/08/17
    List<CrewPost> findAllByMemberId(String id);
}

