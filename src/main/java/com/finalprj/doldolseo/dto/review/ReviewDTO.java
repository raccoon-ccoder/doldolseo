package com.finalprj.doldolseo.dto.review;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.finalprj.doldolseo.domain.Member;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

/*
 * 후기게시판 DTO
 *
 * @Author 김경일
 * @Date 2021/08/06
 */
@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReviewDTO {
    private Long reviewNo;
    private Member member;
    private String title;
    private String content;
    private String courseImgName;
    private String uploadImgNames;
    private LocalDateTime wDate;
    private int hit;
    private int areaNo;

    //DTO Only
    private MultipartFile courseImgFile;
    private String[] uploadImgs;

}
