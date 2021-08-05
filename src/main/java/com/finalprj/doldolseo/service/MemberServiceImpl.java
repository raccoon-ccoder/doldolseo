package com.finalprj.doldolseo.service;

import com.finalprj.doldolseo.dto.MemberDTO;
import com.finalprj.doldolseo.repository.MemberRepository;
import com.finalprj.doldolseo.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

/*
 * 멤버 관련 ServiceImpl
 *
 * @Author 백정연
 * @Date 2021/08/03
 */

@Service
public class MemberServiceImpl implements  MemberService{

    @Autowired
    private MemberRepository repository;

    @Override
    public MemberVO join(MemberDTO memberDTO) throws IOException {
        MemberVO memberVO = dtoToEntity(memberDTO);

        if(memberDTO.getMember_img().getOriginalFilename() != null){
            String rootPath = System.getProperty("user.dir") + "/src/main/resources/static/_image/profile/";
            String filePath = rootPath + memberVO.getMember_img();
            File dest = new File(filePath);
            memberDTO.getMember_img().transferTo(dest);
        }
        repository.save(memberVO);
        return  memberVO;
    }

    @Override
    public int checkId(String id) {
        Optional<MemberVO> result = repository.findById(id);
        return result.isPresent()? 0 : 1;
        // 중복된 아이디가 있으면 0, 없다면 1
    }

    @Override
    public int checkNickname(String nickname) {
        Optional<MemberVO> result = repository.findByNickname(nickname);
        return result.isPresent()? 0 : 1;
        // 중복된 닉네임이 있으면 0, 없다면 1
    }

}
