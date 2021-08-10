package com.finalprj.doldolseo.impl;

import com.finalprj.doldolseo.dto.PlannerDTO;
import com.finalprj.doldolseo.repository.PlannerRepository;
import com.finalprj.doldolseo.entity.Planner;
import com.finalprj.doldolseo.service.PlannerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

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
    public PlannerDTO insertPlanner(PlannerDTO dto) throws ParseException {
        Planner entity = dtoToEntity(dto);
        Planner planner = repository.save(entity);
        PlannerDTO plannerDTO = entityToDto(planner);
        return plannerDTO;
    }

    @Override
    public List<PlannerDTO> selectPlanners(String id) {
        List<Planner> planners = repository.findAllByIdOrderByPlannerNoDesc(id);
        List<PlannerDTO> dto = new ArrayList<PlannerDTO>();
        for(Planner planner : planners){
            dto.add(entityToDto(planner));
        }
        return dto;
    }

    @Override
    public PlannerDTO selectPlanner(Long plannerNo) {
        Planner planner = repository.findByPlannerNo(plannerNo);
        PlannerDTO dto = entityToDto(planner);
        return  dto;
    }

    @Override
    public void deletePlanner(Long plannerNo) {
        repository.deleteByPlannerNo(plannerNo);
    }

}
