package com.finalprj.doldolseo.repository;

/*
 * 플랜 관련 Repository
 *
 * @Author 백정연
 * @Date 2021/08/06
 */

import com.finalprj.doldolseo.domain.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface PlanRepository extends JpaRepository<Plan, Long> {
    List<Plan> findAllByPlannerNoAndDayBetweenOrderByPlanNo(Long plannerNo, Date start,Date end);
    List<Plan> findAllByPlannerNoOrderByPlanNo(Long plannerNo);
    @Transactional
    void deleteAllByPlannerNo(Long plannerNo);
    @Transactional
    void deleteByPlanNo(Long planNo);
}
