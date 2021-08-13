package com.finalprj.doldolseo.repository.crew;

import com.finalprj.doldolseo.domain.crew.Crew;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrewRepository extends JpaRepository<Crew, Long> {

    public boolean existsByCrewName(String crewName);
}
