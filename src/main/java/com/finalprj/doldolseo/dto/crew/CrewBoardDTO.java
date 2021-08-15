package com.finalprj.doldolseo.dto.crew;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDateTime;

/*
 * 크루활동 게시판 DTO
 *
 * @Author 김경일
 * @Date 2021/08/16
 */

@Getter
@Setter
@ToString
public class CrewBoardDTO {
    private Long postNo;
    private Long crewNo;
    private String id;
    private String category;
    private String title;
    private String content;
    private String memberList;
    private String uploadImg;
    private LocalDateTime wDate;
    private Integer hit;
}
