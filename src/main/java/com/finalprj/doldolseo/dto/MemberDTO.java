package com.finalprj.doldolseo.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * 멤버 DTO 클래스
 *
 * @Author 백정연
 * @Date 2021/08/03
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MemberDTO {
    private String id;
    private String password;
    private String name;
    private String nickname;
    private String member_img;
    private Date birth;
    private String gender;
    private String email;
    private String phone;
    private Character crleader;

   public void setBirth(String birth) throws ParseException {
       SimpleDateFormat fm  = new SimpleDateFormat("yyyy-MM-dd");
        Date date_birth = fm.parse(birth);
       System.out.println("setbirth : " + date_birth);
       this.birth = date_birth;
   }
}
