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
    AreaDTO getArea(String name);

    Page<AreaDTO> getAreaPage(AreaDTO dto, Pageable pageable);
}
