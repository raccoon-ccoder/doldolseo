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
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "sigungu")
    private Integer sigungu;

    @Column(name = "zipcode")
    private Integer zipcode;

    @Column(name = "tel")
    private String tel;

    @Column(name = "x")
    private Float x;

    @Column(name = "y")
    private Float y;

    @Column(name = "image1")
    private String image1;

    @Column(name = "image2")
    private String image2;

    @Column(name = "contentid")
    private Long contentid;

}
