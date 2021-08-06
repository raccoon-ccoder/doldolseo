package com.finalprj.doldolseo.service;

import com.finalprj.doldolseo.repository.AreaRepository;
import com.finalprj.doldolseo.vo.AreaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*
 * 지역게시판 Servcie
 *
 * @Author 김경일
 * @Date 2021/08/04
 */

@Service
public class AreaService {

    @Autowired
    private AreaRepository repository;

    //지역명으로 지역정보 상세 조회
    public AreaVO getArea(String name) {
        return repository.findFirstByName(name);
    }


    //지역 목록 조회(시군구코드 + 페이징 처리)
    public Page<AreaVO> getAreaList(Integer sigungu, Integer contentType, Pageable pageable) {
        if (contentType == null) {
            return repository.findBySigungu(sigungu, pageable);
        } else {
            return repository.findBySigunguAndContentType(sigungu, contentType, pageable);
        }
    }

    //검색어 입력시 검색결과 조회
    public Page<AreaVO> getAreaListBySearch(Integer sigungu, String searchKeyword, Pageable pageable) {
        return repository.findBySigunguAndNameContaining(sigungu, searchKeyword, pageable);
    }

    //지역 목록 조회 (전체)
    public List<AreaVO> getAreaList() {
        return repository.findAll();
    }


}
