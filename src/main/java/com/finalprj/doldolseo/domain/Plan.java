package com.finalprj.doldolseo.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/*
 * 플랜 Entity
 *
 * @Author 백정연
 * @Date 2021/08/06
 */

@Entity
@Table(name="PLAN_TBL")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
@Builder
@SequenceGenerator(name = "PLAN_SEQ_GENERATOR", sequenceName = "PLAN_SEQ", initialValue = 1, allocationSize = 1)
public class Plan {

    @Id
    @Column(name = "plan_no", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "PLAN_SEQ_GENERATOR")
    private Long planNo;

    @Column(name = "planner_no", nullable = false)
    private Long plannerNo;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(nullable = false)
    private Date day;

    @Column(nullable = false)
    private String name;

    private String intro;

    @Column(nullable = false)
    private Float x;

    @Column(nullable = false)
    private Float y;
}
