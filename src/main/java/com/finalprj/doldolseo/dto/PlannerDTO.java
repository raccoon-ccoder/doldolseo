package com.finalprj.doldolseo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.finalprj.doldolseo.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

/*
 * 플래너 DTO 클래스
 *
 * @Author 백정연
 * @Date 2021/08/03
 */

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlannerDTO {
    private Long plannerNo;
    private Member member;
    private String title;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lDate;

    private String intro;
    private Date wDate;


//    public void setFDate(String fDate) throws ParseException {
//        SimpleDateFormat fm  = new SimpleDateFormat("yyyy-MM-dd");
//        Date first_date = fm.parse(fDate);
//        this.fDate = first_date;
//    }
//
//    public void setLDate(String lDate) throws ParseException {
//        SimpleDateFormat fm  = new SimpleDateFormat("yyyy-MM-dd");
//        Date last_date = fm.parse(lDate);
//        this.lDate = last_date;
//    }
//
//    public void setWDate(String WDate) throws ParseException {
//        SimpleDateFormat fm  = new SimpleDateFormat("yyyy-MM-dd");
//        Date write_date = fm.parse(WDate);
//        this.wDate = write_date;
//    }
}
