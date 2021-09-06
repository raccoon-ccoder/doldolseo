package com.finalprj.doldolseo.service.impl;

import com.finalprj.doldolseo.dto.MemberDTO;
import com.finalprj.doldolseo.domain.Member;
import com.finalprj.doldolseo.repository.LoginRepository;
import com.finalprj.doldolseo.service.LoginService;
import com.finalprj.doldolseo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

/*
 * 로그인 Service 구현 클래스
 *
 * @Author 백정연
 * @Date 2021/08/03
 */

@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    private LoginRepository repository;

    @Override
    public MemberDTO login(MemberDTO dto) {
        Optional<Member> result = repository.findByIdAndPassword(dto.getId(), dto.getPassword());
        return result.isPresent()? entityToDto(result.get()) : null;
    }

}
