package com.finalprj.doldolseo.dto.crew;

import com.finalprj.doldolseo.domain.Member;
import com.finalprj.doldolseo.domain.crew.Crew;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class CrewMemberDTO {
        private Long regNo;
        private Crew crew;
        private Member member;
        private Boolean state;
        private String answer1;
        private String answer2;
        private String answer3;
}
