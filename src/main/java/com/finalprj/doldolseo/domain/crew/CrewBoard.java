package com.finalprj.doldolseo.domain.crew;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDateTime;

/*
 * 크루 활동 게시판 Entity
 *
 * @Author 김경일
 * @Date 2021/08/16
 */

@Entity
@Table(name = "CREW_BOARD_TBL")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(
        name="CREW_BOARD_SEQ_GEN",
        sequenceName="CREW_BOARD_SEQ",
        allocationSize=1)
@Setter
public class CrewBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CREW_BOARD_SEQ_GEN")
    @Column(name = "CREW_POST_NO")
    private Long postNo;

    @Column(name = "CREW_NO")
    private Long crewNo;

    private String id;
    private String category;
    private String title;
    private String content;

    @Column(name = "MEMBERLIST")
    private String memberList;

    @Column(name = "UPLOAD_IMG")
    private String uploadImg;

    @Column(name = "W_DATE")
    private LocalDateTime wDate;

    private Integer hit;
}
