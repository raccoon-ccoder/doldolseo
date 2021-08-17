package com.finalprj.doldolseo.dto.crew;

import com.finalprj.doldolseo.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class CrewDTO {
    private Long crewNo;
    private Member member;
    private String crewName;
    private String areaList;
    private String intro;
    private String introDetail;
    private String recruit;
    private String question1;
    private String question2;
    private String question3;
    private String crewImage;
    private String grade;
    private Integer crewPoint;
    private LocalDateTime cDate;

    //관심 지역 String[] 전송받을 파라미터
    private String[] areaListValues;
}
