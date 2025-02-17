package com.finalprj.doldolseo.service;


/*
 * 로그인 관련 service
 *
 * @Author 백정연
 * @Date 2021/08/03
 */


import com.finalprj.doldolseo.dto.MemberDTO;
import com.finalprj.doldolseo.domain.Member;

public interface LoginService {
    MemberDTO login(MemberDTO dto);

    default Member dtoToEntity(MemberDTO dto){
        Member entity = Member.builder()
                .id(dto.getId())
                .password(dto.getPassword())
                .name(dto.getName())
                .nickname(dto.getNickname())
                .gender(dto.getGender())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .birth(dto.getBirth())
                .member_img(dto.getMember_img())
                .crleader(dto.getCrleader())
                .build();
        return entity;
    }

    default MemberDTO entityToDto(Member member){
        MemberDTO dto = MemberDTO.builder()
                .id(member.getId())
                .password(member.getPassword())
                .name(member.getName())
                .nickname(member.getNickname())
                .gender(member.getGender())
                .email(member.getEmail())
                .phone(member.getPhone())
                .birth(member.getBirth())
                .member_img(member.getMember_img())
                .crleader(member.getCrleader())
                .build();
        return dto;
    }
}
