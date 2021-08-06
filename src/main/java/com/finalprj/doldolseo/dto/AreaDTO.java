package com.finalprj.doldolseo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 * 지역게시판 DTO
 *
 * @Author 김경일
 * @Date 2021/08/06
 */

@Getter
@Setter
@ToString
public class AreaDTO {
    private String name;
    private String address;
    private Integer sigungu;
    private Integer zipcode;
    private String tel;
    private Float x;
    private Float y;
    private String image1;
    private String image2;
    private Integer contentType;
    private Long contentId;

    //검색어
    private String searchKeyword;


}
