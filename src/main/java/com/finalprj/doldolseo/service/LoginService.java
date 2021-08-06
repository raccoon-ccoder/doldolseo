package com.finalprj.doldolseo.service;


/*
 * 멤버 관련 service
 *
 * @Author 백정연
 * @Date 2021/08/03
 */


import com.finalprj.doldolseo.dto.MemberDTO;
import com.finalprj.doldolseo.vo.MemberVO;

public interface LoginService {
    MemberVO login(MemberDTO dto);
}
