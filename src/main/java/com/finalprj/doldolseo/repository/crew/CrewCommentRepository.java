package com.finalprj.doldolseo.repository.crew;

import com.finalprj.doldolseo.domain.crew.CrewComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CrewCommentRepository extends JpaRepository<CrewComment, Long> {

    List<CrewComment> findAllByCrewPost_PostNo(Long postNo);

    CrewComment findByCommentNo(Long commentNo);

}
