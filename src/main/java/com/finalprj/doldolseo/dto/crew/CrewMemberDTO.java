package com.finalprj.doldolseo.dto.crew;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CrewMemberDTO {
        private Long regNo;
        private Long crewNo;
        private String id;
        private Boolean state;
        private String answer1;
        private String answer2;
        private String answer3;
}
