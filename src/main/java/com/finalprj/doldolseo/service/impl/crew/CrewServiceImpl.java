package com.finalprj.doldolseo.service.impl.crew;

import com.finalprj.doldolseo.domain.crew.Crew;
import com.finalprj.doldolseo.dto.crew.CrewDTO;
import com.finalprj.doldolseo.repository.crew.CrewRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
}
