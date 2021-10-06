package com.finalprj.doldolseo.controller;

import com.finalprj.doldolseo.domain.Member;
import com.finalprj.doldolseo.domain.Planner;
import com.finalprj.doldolseo.dto.MemberDTO;
import com.finalprj.doldolseo.dto.PlanDTO;
import com.finalprj.doldolseo.dto.PlannerDTO;
import com.finalprj.doldolseo.service.impl.PlanServiceImpl;
import com.finalprj.doldolseo.service.impl.PlannerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/*
 *  플래너 Controller
 *
 * @Author 백정연
 * @Date 2021/08/03
 */


@Controller
public class PlannerController {

    @Autowired
    private PlannerServiceImpl plannerService;

    @Autowired
    private PlanServiceImpl planService;

    // 플래너 상세 조회
    @RequestMapping("/planD")
    public String planDetail(PlannerDTO dto, Model model) throws Exception {
        PlannerDTO planner = plannerService.selectPlanner(dto.getPlannerNo());
        List<Date> dates = planService.getDiffDays(planner.getFDate(), planner.getLDate());
        List<PlanDTO> plans = planService.selectPlan(dto.getPlannerNo());
        model.addAttribute("planner", planner);
        model.addAttribute("dates", dates);
        model.addAttribute("plans", plans);

        return "/mypage/plan/planDetail";
    }

    // 플래너 목록 조회
    @RequestMapping("/planL")
    public String planList(MemberDTO dto, Model model) throws Exception {
        List<PlannerDTO> planners = plannerService.selectPlanners(dto.getId());
        List<PlanDTO> plans = planService.joinPlans(planners);
        model.addAttribute("planners", planners);
        model.addAttribute("plans", plans);

        return "/mypage/plan/planList";
    }

    // 플래너 저장
    @RequestMapping(value = "/plannerInsert", method = RequestMethod.POST)
    @ResponseBody
    public String planInsert(@RequestBody PlanDTO param) throws ParseException {
       Planner planner = plannerService.insertPlanner(param.getPlanner());
       planService.insertPlan(param.getPlanList(), planner);
       return "planL?id=" + planner.getMember().getId();
    }

    // 플래너 수정
    @RequestMapping(value = "/plannerUpdate", method = RequestMethod.POST)
    @ResponseBody
    public String plannerUpdate(@RequestBody PlanDTO param) throws ParseException {
        Planner planner = plannerService.insertPlanner(param.getPlanner());
        planService.updatePlans(param.getPlanList(), planner);
        return "planD?plannerNo=" + planner.getPlannerNo();
    }

    // 플래너 삭제
    @RequestMapping("plannerDelete")
    public String deletePlanner(PlannerDTO dto, Model model) {
        planService.deletePlans(dto.getPlannerNo());
        plannerService.deletePlanner(dto.getPlannerNo());

        return "redirect:/planL?id=" + dto.getMember().getId();
    }

    // 플래너 작성 폼
    @RequestMapping("/goPlanI")
    public String goIsertPage(PlannerDTO dto, Model model) throws Exception {
        List<Date> days = planService.getDiffDays(dto.getFDate(), dto.getLDate());
        model.addAttribute("days", days);
        model.addAttribute("planner_user", dto);

        return "/mypage/plan/planInsert";
    }

    // 플래너 수정 폼
    @RequestMapping("/goPlanU")
    public String goUpdatePage(PlannerDTO dto, Model model) throws Exception {
        List<Date> days = planService.getDiffDays(dto.getFDate(), dto.getLDate());
        List<PlanDTO> plans = planService.selectPlan(dto.getPlannerNo());
        model.addAttribute("days", days);
        model.addAttribute("planner_user", dto);
        model.addAttribute("plans", plans);

        return "/mypage/plan/planUpdate";
    }
}