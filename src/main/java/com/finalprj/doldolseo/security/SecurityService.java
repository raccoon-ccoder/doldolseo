package com.finalprj.doldolseo.security;

import com.finalprj.doldolseo.domain.Member;
import com.finalprj.doldolseo.dto.MemberDTO;
import com.finalprj.doldolseo.repository.LoginRepository;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspContext;

/*
 * DB에서 유저 정보를 가져와 처리하는 클래스
 *
 * @Author 백정연
 * @Date 2021/08/14
 */

@Service
public class SecurityService implements UserDetailsService {

    @Autowired
    private LoginRepository loginRepository;

    // MemberRepository로 회원 정보 조회
    // UserDetails 타입의 객체로 리턴
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Member member = loginRepository.findById(id).get();

        if(member == null){
            return null;
        }

        MemberDTO memberDTO = MemberDTO.builder()
                .id(member.getId())
                .password(member.getPassword())
                .name(member.getName())
                .nickname(member.getNickname())
                .email(member.getEmail())
                .phone(member.getPhone())
                .birth(member.getBirth())
                .gender(member.getGender())
                .member_img(member.getMember_img())
                .crleader(member.getCrleader())
                .build();

        return new SecurityDetails(memberDTO);
        // 시큐리티의 세션에 유저정보 저장되는 시점
    }
}
