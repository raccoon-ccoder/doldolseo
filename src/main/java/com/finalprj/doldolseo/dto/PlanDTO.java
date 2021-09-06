package com.finalprj.doldolseo.dto;

import com.finalprj.doldolseo.domain.Planner;
import lombok.*;

import java.util.Date;

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
    private Date day;
    private String name;
    private String intro;
    private Float x;
    private Float y;
}
