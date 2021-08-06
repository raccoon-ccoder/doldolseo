package com.finalprj.doldolseo.service;

import com.finalprj.doldolseo.dto.MemberDTO;
import com.finalprj.doldolseo.repository.LoginRepository;
import com.finalprj.doldolseo.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    private LoginRepository repository;

    @Override
    public MemberVO login(MemberDTO dto) {
        Optional<MemberVO> result = repository.findByIdAndPassword(dto.getId(), dto.getPassword());
        return result.isPresent()? result.get() : null;
    }
}
