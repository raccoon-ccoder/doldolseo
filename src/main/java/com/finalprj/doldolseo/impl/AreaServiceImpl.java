package com.finalprj.doldolseo.impl;

import com.finalprj.doldolseo.dto.AreaDTO;
import com.finalprj.doldolseo.domain.Area;
import com.finalprj.doldolseo.repository.AreaRepository;
import com.finalprj.doldolseo.service.AreaService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/*
 * 지역게시판 Servcie
 *
 * @Author 김경일
 * @Date 2021/08/04
 */

@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaRepository repository;

    @Autowired
    private ModelMapper modelMapper;


    //지역명으로 지역정보 상세 조회
    @Override
    public AreaDTO getArea(String name) {

        Area entity = repository.findFirstByName(name);
        //Area to AreaDTO
        AreaDTO area = modelMapper.map(entity, AreaDTO.class);

        return area;
    }

    //지역 목록 조회(시군구코드 + 페이징 처리)
    @Override
    public Page<AreaDTO> getAreaList(AreaDTO dto, Pageable pageable) {

        Page<Area> entityPage;

        if (dto.getContentType() == null) {
            entityPage = repository.findBySigungu(dto.getSigungu(), pageable);
        } else {
            entityPage = repository.findBySigunguAndContentType(dto.getSigungu(), dto.getContentType(), pageable);
        }

        //Page<Area> to Page<AreaDTO>
        Page<AreaDTO> areaList = modelMapper.map(entityPage, new TypeToken<Page<AreaDTO>>() {
        }.getType());

        return areaList;
    }


    //검색어 입력시 검색결과 조회
    @Override
    public Page<AreaDTO> getAreaListBySearch(AreaDTO dto, Pageable pageable) {
        Page<Area> entityPage = repository.findBySigunguAndNameContaining(dto.getSigungu(), dto.getSearchKeyword(), pageable);

        Page<AreaDTO> areaList = modelMapper.map(entityPage, new TypeToken<Page<AreaDTO>>() {
        }.getType());

        return areaList;
    }

}
