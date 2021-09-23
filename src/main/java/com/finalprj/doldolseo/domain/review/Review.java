package com.finalprj.doldolseo.domain.review;

import com.finalprj.doldolseo.domain.Member;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/*
 * 후기게시판 Entity
 *
 * @Author 김경일
 * @Date 2021/08/06
 */

@Entity
@Table(name = "REVIEW_BOARD_TBL")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(
        name="REVIEW_BOARD_SEQ_GEN",
        sequenceName="REVIEW_BOARD_SEQ",
        allocationSize=1)
@Setter
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REVIEW_BOARD_SEQ_GEN")
    @Column(name = "REVIEW_NO")
    private Long reviewNo;

    @ManyToOne
    @JoinColumn(name = "ID")
    private Member member;

    private String title;
    private String content;

    @Column(name = "COURSE_IMG")
    private String courseImgName;

    @Column(name = "UPLOAD_IMG")
    private String uploadImgNames;

    @Column(name = "W_DATE")
    private LocalDateTime wDate;

    private int hit;

    @Column(name = "AREA_NO")
    private int areaNo;
}
