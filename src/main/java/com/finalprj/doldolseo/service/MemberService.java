package com.finalprj.doldolseo.service;

import com.finalprj.doldolseo.dto.MemberDTO;
import com.finalprj.doldolseo.entity.MemberVO;

import java.io.IOException;

/*
 * 멤버 관련 service
 *
 * @Author 백정연
 * @Date 2021/08/03
 */

public interface MemberService {
    MemberVO join(MemberDTO memberDTO) throws IOException;

    int checkId(String id);

    int checkNickname(String nickname);

    default MemberVO dtoToEntity(MemberDTO dto){
        String fileName = "";
        if(dto.getMember_img().getOriginalFilename().length() > 0){
            String file = dto.getMember_img().getOriginalFilename();
            fileName = dto.getId() + "." + file.substring(file.lastIndexOf(".") + 1);
        }else{
            fileName = "sample.png";
        }

        MemberVO entity = MemberVO.builder()
                .id(dto.getId())
                .password(dto.getPassword())
                .name(dto.getName())
                .nickname(dto.getNickname())
                .gender(dto.getGender())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .birth(dto.getBirth())
                .member_img(fileName)
                .crleader(dto.getCrleader())
                .build();
        return entity;
    }
}
