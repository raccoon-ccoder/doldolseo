package com.finalprj.doldolseo.service;

import com.finalprj.doldolseo.entity.Plan;
import com.finalprj.doldolseo.entity.Planner;

import java.util.List;

public interface PlanService {
    List<Plan> selectPlans(Planner planner);
    List<Plan> joinPlans(List<Planner> planners);
    List<Plan> selectPlan(Long plannerNo);
    void deletePlans(Long plannerNo);
    void deletePlan(Long planNo);
}
