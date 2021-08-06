package com.finalprj.doldolseo.vo;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * 지역게시판 VO클래스
 *
 * @Author 김경일
 * @Date 2021/08/04
 */

@Entity
@Table(name = "SEOUL_AREA_TBL")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class AreaVO {

    @Id
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
}
