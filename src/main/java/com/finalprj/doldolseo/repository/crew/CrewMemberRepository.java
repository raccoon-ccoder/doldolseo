package com.finalprj.doldolseo.repository.crew;

import com.finalprj.doldolseo.domain.crew.CrewMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CrewMemberRepository extends JpaRepository<CrewMember, Long> {

    Boolean existsByCrewCrewNoAndMemberId(Long crewNo, String id);

    List<CrewMember> findByCrewCrewNoAndState(Long crewNo, boolean state);

    CrewMember findByRegNo(Long regNo);

    List<CrewMember> findAllByMemberId(String id);

    int countCrewMemberByMemberId(String id);

    // 사용자가 가입한 크루 탈퇴
    // @Author 백정연, @Date 2021/08/18
    @Transactional
    void deleteAllByMemberId(String id);

    boolean existsByMemberId(String id);

    CrewMember findByMemberIdAndCrewCrewNo(String id , Long crewNo);
}
