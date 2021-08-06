package com.finalprj.doldolseo.service;

import com.finalprj.doldolseo.dto.AreaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/*
 * 지역게시판 Service
 *
 * @Author 김경일
 * @Date 2021/08/06
 */
public interface AreaService {

    //지역명으로 지역정보 상세 조회
    public AreaDTO getArea(String name);

    //지역 목록 조회(시군구코드 + 페이징 처리)
    public Page<AreaDTO> getAreaList(AreaDTO dto, Pageable pageable);

    //검색어 입력시 검색결과 조회
    public Page<AreaDTO> getAreaListBySearch(AreaDTO dto, Pageable pageable);
}
