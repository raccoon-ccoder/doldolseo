package com.finalprj.doldolseo.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 로그인 실패시 실행되는 Handler
 *
 * @Author 백정연
 * @Date 2021/08/14
 */

public class LoginFailHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception){
        try{
            request.setAttribute("result","false");
            request.getRequestDispatcher("/doldolseo/memberL").forward(request,response);
        }catch (Exception e){
            System.out.println("LoginFailHandler err : " + e.getMessage());
        }
    }
}
