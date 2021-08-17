package com.finalprj.doldolseo.repository.crew;

import com.finalprj.doldolseo.domain.crew.CrewMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CrewMemberRepository extends JpaRepository<CrewMember, Long> {

    Boolean existsByCrewCrewNoAndMemberId(Long crewNo, String id);

    List<CrewMember> findByCrewCrewNoAndState(Long crewNo, boolean state);

    CrewMember findByRegNo(Long regNo);

    List<CrewMember> findAllByMemberId(String id);
}
