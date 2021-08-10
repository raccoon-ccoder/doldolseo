package com.finalprj.doldolseo.impl;

import com.finalprj.doldolseo.dto.MemberDTO;
import com.finalprj.doldolseo.repository.LoginRepository;
import com.finalprj.doldolseo.service.LoginService;
import com.finalprj.doldolseo.entity.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/*
 * 로그인 Service 구현 클래스
 *
 * @Author 백정연
 * @Date 2021/08/03
 */

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginRepository repository;

    @Override
    public MemberVO login(MemberDTO dto) {
        Optional<MemberVO> result = repository.findByIdAndPassword(dto.getId(), dto.getPassword());
        return result.isPresent()? result.get() : null;
    }
}
