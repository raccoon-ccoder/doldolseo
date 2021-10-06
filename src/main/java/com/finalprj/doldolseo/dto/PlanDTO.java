package com.finalprj.doldolseo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.finalprj.doldolseo.domain.Planner;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/*
 * 플랜 DTO
 *
 * @Author 백정연
 * @Date 2021/08/06
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanDTO {
    private Long planNo;
    private Planner planner;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+9")
    private Date day;
    private String name;
    private String intro;
    private Float x;
    private Float y;

    private List<PlanDTO> planList;
}
