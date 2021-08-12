package com.finalprj.doldolseo.dto.review;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ReviewCommentDTO {
    private Long commentNo;
    private Long reviewNo;
    private String id;
    private String content;
    private LocalDateTime wDate;
}
