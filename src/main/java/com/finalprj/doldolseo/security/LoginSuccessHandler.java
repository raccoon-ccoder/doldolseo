package com.finalprj.doldolseo.security;

import com.finalprj.doldolseo.dto.MemberDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * 로그인 성공시 실행되는 Handler
 *
 * @Author 백정연
 * @Date 2021/08/14
 */

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        try{
            MemberDTO dto = ((SecurityDetails)authentication.getPrincipal()).getMemberDTO();
            HttpSession session = request.getSession();
            session.setAttribute("member",dto);
            response.sendRedirect("/main");
        }catch (Exception e){
            System.out.println("LoginSuccessHandler err : " + e.getMessage());
        }
    }
}
