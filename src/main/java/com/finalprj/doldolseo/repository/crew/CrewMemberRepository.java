package com.finalprj.doldolseo.repository.crew;

import com.finalprj.doldolseo.domain.crew.CrewMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CrewMemberRepository extends JpaRepository<CrewMember, Long> {

    Boolean existsByCrewNoAndId(Long crewNo, String id);

    List<CrewMember> findByCrewNoAndState(Long crewNo, boolean state);

    CrewMember findByRegNo(Long regNo);
}
