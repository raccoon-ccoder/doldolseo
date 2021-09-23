package com.finalprj.doldolseo.repository;

import com.finalprj.doldolseo.domain.Area;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * 지역게시판 Resository
 *
 * @Author 김경일
 * @Date 2021/08/04
 */

@Repository
public interface AreaRepository extends JpaRepository<Area, String> {
    Area findFirstByName(String name);

    Page<Area> findBySigunguAndContentType(Integer sigungu, Integer contentType, Pageable pageable);

    Page<Area> findBySigungu(Integer sigungu, Pageable pageable);

    Page<Area> findBySigunguAndNameContaining(Integer sigungu, String name, Pageable pageable);
}
