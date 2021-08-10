package com.finalprj.doldolseo.service;

import com.finalprj.doldolseo.dto.PlanDTO;
import com.finalprj.doldolseo.dto.PlannerDTO;
import com.finalprj.doldolseo.entity.Plan;
import com.finalprj.doldolseo.entity.Planner;

import java.util.List;

public interface PlanService {
    List<PlanDTO> selectPlans(PlannerDTO planner);
    List<PlanDTO> joinPlans(List<PlannerDTO> planners);
    List<PlanDTO> selectPlan(Long plannerNo);
    void deletePlans(Long plannerNo);
    void deletePlan(Long planNo);

    default PlanDTO entityToDto(Plan plan){
        PlanDTO dto = PlanDTO.builder()
                .planNo(plan.getPlanNo())
                .plannerNo(plan.getPlannerNo())
                .name(plan.getName())
                .intro(plan.getIntro())
                .day(plan.getDay())
                .x(plan.getX())
                .y(plan.getY())
                .build();
        return dto;
    }

    default Plan dtoToEntity(PlanDTO dto){
        Plan plan = Plan.builder()
                .planNo(dto.getPlanNo())
                .plannerNo(dto.getPlannerNo())
                .name(dto.getName())
                .intro(dto.getIntro())
                .day(dto.getDay())
                .x(dto.getX())
                .y(dto.getY())
                .build();
        return plan;
    }
}
