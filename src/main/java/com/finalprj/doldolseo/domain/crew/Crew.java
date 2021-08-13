package com.finalprj.doldolseo.domain.crew;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "CREW_TBL")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(
        name = "CREW_SEQ_GEN",
        sequenceName = "CREW_SEQ",
        allocationSize = 1)
@Setter
@DynamicInsert
public class Crew {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CREW_SEQ_GEN")
    @Column(name = "CREW_NO")
    private Long crewNo;

    private String id;

    @Column(name = "CREW_NAME")
    private String crewName;

    @Column(name = "AREALIST")
    private String areaList;

    private String intro;

    @Column(name = "INTRO_DETAIL")
    private String introDetail;
    private String recruit;
    private String question1;
    private String question2;
    private String question3;

    @Column(name = "CREW_IMG")
    private String crewImage;


    private String grade;

    @Column(name = "CREW_POINT")
    private Integer crewPoint;

    @Column(name = "C_DATE")
    private LocalDateTime cDate;
}
