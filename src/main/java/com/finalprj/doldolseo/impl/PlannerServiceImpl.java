package com.finalprj.doldolseo.impl;

import com.finalprj.doldolseo.dto.PlannerDTO;
import com.finalprj.doldolseo.repository.PlannerRepository;
import com.finalprj.doldolseo.entity.Planner;
import com.finalprj.doldolseo.service.PlannerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

/*
 * 플래너 Service 구현 클래스
 *
 * @Author 백정연
 * @Date 2021/08/06
 */

@Service
public class PlannerServiceImpl implements PlannerService {
    @Autowired
    private PlannerRepository repository;

    @Override
    public Planner insertPlanner(PlannerDTO dto) throws ParseException {
        Planner entity = dtoToEntity(dto);
        Planner planner = repository.save(entity);
        return planner;
    }

    @Override
    public List<Planner> selectPlanners(String id) {
        List<Planner> planners = repository.findAllByIdOrderByPlannerNoDesc(id);
        return planners;
    }

    @Override
    public Planner selectPlanner(Long plannerNo) {
        Planner planner = repository.findByPlannerNo(plannerNo);
        return  planner;
    }

    @Override
    public void deletePlanner(Long plannerNo) {
        repository.deleteByPlannerNo(plannerNo);
    }

    @Override
    public Long countPlanner(Long plannerNo) {
        Long result = repository.countByPlannerNo(plannerNo);
        return result;
    }
}
