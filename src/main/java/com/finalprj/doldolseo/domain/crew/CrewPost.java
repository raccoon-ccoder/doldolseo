package com.finalprj.doldolseo.domain.crew;

import com.finalprj.doldolseo.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.jpa.repository.EntityGraph;

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
@DynamicInsert
public class CrewPost {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CREW_BOARD_SEQ_GEN")
    @Column(name = "CREW_POST_NO")
    private Long postNo;

    @JoinColumn(name = "CREW_NO")
    @ManyToOne
    private Crew crew;

    @ManyToOne
    @JoinColumn(name = "ID")
    private Member member;

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
