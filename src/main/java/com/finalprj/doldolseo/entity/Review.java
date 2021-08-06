package com.finalprj.doldolseo.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

/*
 * 후기게시판 Entity
 *
 * @Author 김경일
 * @Date 2021/08/06
 */

@Entity
@Table(name = "REVIEW_BOARD_TBL")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {
    @Id
    @GeneratedValue
    @Column(name = "REVIEW_NO")
    private Long reviewNo;

    private String id;
    private String title;
    private String content;

    @Column(name = "COURSE_IMG")
    private String coureImg;

    @Column(name = "UPLOAD_IMG")
    private String uploadImg;

    @Column(name = "W_DATE")
    @Temporal(TemporalType.DATE)
    private Date wDate;

    private int hit;

    @Column(name = "AREA_NO")
    private int areaNo;
}
