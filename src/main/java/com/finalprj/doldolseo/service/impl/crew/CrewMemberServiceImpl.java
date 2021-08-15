package com.finalprj.doldolseo.service.impl.crew;

import com.finalprj.doldolseo.domain.Member;
import com.finalprj.doldolseo.domain.crew.CrewMember;
import com.finalprj.doldolseo.dto.MemberDTO;
import com.finalprj.doldolseo.dto.crew.CrewMemberDTO;
import com.finalprj.doldolseo.repository.MemberRepository;
import com.finalprj.doldolseo.repository.crew.CrewMemberRepository;
import com.finalprj.doldolseo.repository.crew.CrewRepository;
import com.finalprj.doldolseo.service.impl.MemberServiceImpl;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CrewMemberServiceImpl {

    @Autowired
    CrewMemberRepository crewMemberRepository;

    @Autowired
    CrewRepository crewRepository;

    @Autowired
    ModelMapper modelMapper;

    //크루원 조회
    public CrewMemberDTO getCrewMember(Long regNo){
        CrewMember crewMember = crewMemberRepository.findByRegNo(regNo);
        CrewMemberDTO dto = modelMapper.map(crewMember, CrewMemberDTO.class);

        return dto;
    }

    //크루원 등록
    public void insertCrewMember(CrewMemberDTO dto) {
        CrewMember crewMember = modelMapper.map(dto, CrewMember.class);
        crewMemberRepository.save(crewMember);
    }

    //크루번호로 크루가입자 명단 조회
    public List<CrewMemberDTO> getCrewMembers(Long crewNo) {
        List<CrewMember> crewMembersEntity = crewMemberRepository.findByCrewNoAndState(crewNo,true);
        List<CrewMemberDTO> crewMember = modelMapper.map(crewMembersEntity, new TypeToken<List<CrewMemberDTO>>() {
        }.getType());

        return crewMember;
    }

    //크루번호로 크루가입대기자 명단 조회
    public List<CrewMemberDTO> getWatingMember(Long crewNo) {
        List<CrewMember> crewMembersEntity = crewMemberRepository.findByCrewNoAndState(crewNo,false);
        List<CrewMemberDTO> WatingMember = modelMapper.map(crewMembersEntity, new TypeToken<List<CrewMemberDTO>>() {
        }.getType());

        return WatingMember;
    }

    //크루번호, 크루원id로 해당 크루 가입여부 확인
    public Boolean hasThisCrewMember(Long crewNo, String id) {
        return crewMemberRepository.existsByCrewNoAndId(crewNo, id);
    }

    //크루 가입 승인
    @Transactional
    public void updateCrew(Long regNo){
        CrewMember member = crewMemberRepository.findByRegNo(regNo);
        member.setState(true);
    }

    //크루 가입 거절 및 회원 강퇴
    public void deleteCrew(Long regNo){
        crewMemberRepository.deleteById(regNo);
    }

}
