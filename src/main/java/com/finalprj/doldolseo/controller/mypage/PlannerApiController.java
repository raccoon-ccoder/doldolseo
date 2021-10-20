package com.finalprj.doldolseo.controller.mypage;


/*
 *  플래너 API Controller
 *
 * @Author 백정연
 * @Date 2021/10/11
 */

import com.finalprj.doldolseo.domain.Planner;
import com.finalprj.doldolseo.dto.MemberDTO;
import com.finalprj.doldolseo.dto.PlanDTO;
import com.finalprj.doldolseo.dto.PlannerDTO;
import com.finalprj.doldolseo.service.impl.PlanServiceImpl;
import com.finalprj.doldolseo.service.impl.PlannerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PlannerApiController {
    @Autowired
    private PlannerServiceImpl plannerService;

    @Autowired
    private PlanServiceImpl planService;

    // 플래너 저장
    @PostMapping(value = "/users/{userid}/planners")
    public ResponseEntity<PlannerDTO> planInsert(@RequestBody PlanDTO param,@PathVariable("userid") String userId) {
        Planner planner = plannerService.insertPlanner(param.getPlanner());
        planService.insertPlan(param.getPlanList(), planner);

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/users/{userid}/planners/{plannerid}")
                .buildAndExpand(userId, planner.getPlannerNo())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    // 플래너 수정
    @PutMapping("/users/{userid}/planners/{plannerid}/edit")
    public void plannerUpdate(@RequestBody PlanDTO param, @PathVariable("userid") String userId) throws ParseException {
        Planner planner = plannerService.insertPlanner(param.getPlanner());
        planService.updatePlans(param.getPlanList(), planner);
    }

    // 플래너 삭제
    @DeleteMapping("/users/{userid}/planners/{plannerid}")
    public void deletePlanner(@PathVariable("plannerid") long plannerId) {
        planService.deletePlans(plannerId);
        plannerService.deletePlanner(plannerId);
    }
}
