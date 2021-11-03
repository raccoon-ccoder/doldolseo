package com.finalprj.doldolseo.controller.mypage;


/*
 *  플래너 API Controller
 *
 * @Author 백정연
 * @Date 2021/10/11
 */

import com.finalprj.doldolseo.domain.Plan;
import com.finalprj.doldolseo.domain.Planner;
import com.finalprj.doldolseo.dto.MemberDTO;
import com.finalprj.doldolseo.dto.PlanDTO;
import com.finalprj.doldolseo.dto.PlannerDTO;
import com.finalprj.doldolseo.service.impl.PlanServiceImpl;
import com.finalprj.doldolseo.service.impl.PlannerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<String> planInsert(@RequestBody PlanDTO param,@PathVariable("userid") String userId) {
        Planner planner = plannerService.insertPlanner(param.getPlanner());
        List<Plan> plans = planService.insertPlan(param.getPlanList(), planner);

        if(planner == null || plans == null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("플래너 저장에 실패하였습니다, 다시 작성해주세요.");
        }

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/users/{userid}/planners/{plannerid}")
                .buildAndExpand(userId, planner.getPlannerNo())
                .toUri();

        return ResponseEntity.created(location).body("플래너가 저장되었습니다.");
    }

    // 플래너 수정
    @PutMapping("/users/{userid}/planners/{plannerid}/edit")
    public ResponseEntity<String> plannerUpdate(@RequestBody PlanDTO param, @PathVariable("userid") String userId) throws ParseException {
        Planner planner = plannerService.insertPlanner(param.getPlanner());
        List<Plan> plans = planService.updatePlans(param.getPlanList(), planner);

        if(planner == null || plans == null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("플래너 수정에 실패하였습니다, 다시 작성해주세요.");
        }

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/users/{userid}/planners/{plannerid}")
                .buildAndExpand(userId, planner.getPlannerNo())
                .toUri();

        return ResponseEntity.created(location).body("플래너가 수정되었습니다.");
    }

    // 플래너 삭제
    @DeleteMapping("/users/{userid}/planners/{plannerid}")
    public ResponseEntity<String> deletePlanner(@PathVariable("plannerid") long plannerId, @PathVariable("userid") String userId) {
        PlannerDTO dto = plannerService.selectPlanner(plannerId);
        if(dto == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 플래너가 존재하지 않습니다, 다시 확인해주세요.");
        }
        planService.deletePlans(plannerId);
        plannerService.deletePlanner(plannerId);

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/users/{userid}/planners")
                .buildAndExpand(userId)
                .toUri();

        return ResponseEntity.created(location).body("플래너가 삭제되었습니다.");
    }
}
