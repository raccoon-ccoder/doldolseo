package com.finalprj.doldolseo.repository;

/*
 * 플래너 관련 Repository
 *
 * @Author 백정연
 * @Date 2021/08/06
 */

import com.finalprj.doldolseo.domain.Member;
import com.finalprj.doldolseo.domain.Planner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PlannerRepository extends JpaRepository<Planner, Long> {
    List<Planner> findAllByMemberIdOrderByPlannerNoDesc(String id);
    Planner findByPlannerNo(Long plannerNo);
    @Transactional
    void deleteByPlannerNo(Long plannerNo);
}
