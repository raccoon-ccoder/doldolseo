package com.finalprj.doldolseo.dto;

import lombok.*;
import java.util.Date;

/*
 * 후기게시판 DTO
 *
 * @Author 김경일
 * @Date 2021/08/06
 */
@Getter
@Setter
@ToString
public class ReviewDTO {
        private Long reviewNo;
        private String id;
        private String title;
        private String content;
        private String coureImg;
        private String uploadImg;
        private Date wDate;
        private int hit;
        private int areaNo;
}
