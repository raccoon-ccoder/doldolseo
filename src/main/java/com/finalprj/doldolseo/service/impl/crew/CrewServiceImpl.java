package com.finalprj.doldolseo.service.impl.crew;

import com.finalprj.doldolseo.domain.Member;
import com.finalprj.doldolseo.domain.crew.Crew;
import com.finalprj.doldolseo.dto.MemberDTO;
import com.finalprj.doldolseo.dto.crew.CrewDTO;
import com.finalprj.doldolseo.repository.MemberRepository;
import com.finalprj.doldolseo.repository.crew.CrewRepository;
import com.finalprj.doldolseo.service.impl.MemberServiceImpl;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class CrewServiceImpl {
    @Autowired
    CrewRepository repository;

    @Autowired
    ModelMapper modelMapper;

    /* 크루 목록 조회 */
    public Page<CrewDTO> getCrewList(Pageable pageable) {
        Page<Crew> crewEntities = repository.findAll(pageable);
        Page<CrewDTO> crews = modelMapper.map(crewEntities, new TypeToken<Page<CrewDTO>>() {
        }.getType());

        return crews;
    }

    /* 크루명 중복 체크 */
    public boolean checkCrewName(String crewName) {
        return repository.existsByCrewName(crewName);
    }

    /* 크루 생성 */
    public Crew insertCrew(CrewDTO dto) {
        dto.setCDate(LocalDateTime.now());
        Crew crew = modelMapper.map(dto, Crew.class);

        return repository.save(crew);
    }

    /* 크루 상세 조회 */
    public CrewDTO getCrew(Long crewNo) {
        Crew crewEntity = repository.findByCrewNo(crewNo);
        CrewDTO crew = modelMapper.map(crewEntity, CrewDTO.class);

        return crew;
    }

    /* 크루 상세 조회 */
    public CrewDTO getCrewById(String id) {
        Crew crewEntity = repository.findByMemberId(id);
        CrewDTO crew = modelMapper.map(crewEntity, CrewDTO.class);

        return crew;
    }

    /* 크루 정보 수정 */
    @Transactional
    public void updateCrew(CrewDTO dto, String action) {

        Crew crewEntity = repository.findByCrewNo(dto.getCrewNo());

        switch (action) {
            case "img":
                crewEntity.setCrewImage(dto.getCrewImage());
                break;
            case "area":
                String areaList = String.join(",", dto.getAreaListValues());
                crewEntity.setAreaList(areaList);
                break;
            case "intro":
                crewEntity.setIntro(dto.getIntro());
                break;
            case "introD":
                crewEntity.setIntroDetail(dto.getIntroDetail());
                break;
            case "recruit":
                crewEntity.setRecruit(dto.getRecruit());
                break;
            case "question":
                crewEntity.setQuestion1(dto.getQuestion1());
                crewEntity.setQuestion2(dto.getQuestion2());
                crewEntity.setQuestion3(dto.getQuestion3());
                break;
        }

    }

    @Transactional
    public void updateCrewMaster(Long crewNo, Member member) {
        Crew crew = repository.findByCrewNo(crewNo);
        System.out.println("멤버:"+member.toString());
        crew.setMember(member);
    }
}
