package com.finalprj.doldolseo.controller;

import com.finalprj.doldolseo.dto.MemberDTO;
import com.finalprj.doldolseo.dto.PlanDTO;
import com.finalprj.doldolseo.dto.PlannerDTO;
import com.finalprj.doldolseo.service.impl.PlanServiceImpl;
import com.finalprj.doldolseo.service.impl.PlannerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

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

    // 플래너 생성
    @RequestMapping(value = "/plannerInsert", method = RequestMethod.POST)
    @ResponseBody
    public String planInsert(@RequestParam(value = "date[]") List<String> date, @RequestParam(value = "place[]") List<String> place, @RequestParam(value = "plan_intro[]") List<String> plan_intro, @RequestParam(value = "y[]") List<String> y, @RequestParam(value = "x[]") List<String> x, @RequestParam(value = "time[]") List<String> time, PlannerDTO dto) throws ParseException {
        PlannerDTO plannerDTO = plannerService.insertPlanner(dto);

        List<Date> days = planService.changeDateList(date, time);
        List<Float> float_x = planService.changeFloatList(x);
        List<Float> float_y = planService.changeFloatList(y);

        List<PlanDTO> plans = planService.returnPlan(days, place, plan_intro, float_x, float_y, plannerDTO.getPlannerNo());
        planService.insertPlan(plans);
        return "planL?id=" + dto.getMember().getId();
    }

    // 플래너 수정
    @RequestMapping(value = "/plannerUpdate", method = RequestMethod.POST)
    @ResponseBody
    public String plannerUpdate(@RequestParam(value = "planNo[]") List<String> planNo, @RequestParam(value = "date[]") List<String> date, @RequestParam(value = "place[]") List<String> place, @RequestParam(value = "planIntro[]") List<String> planIntro, @RequestParam(value = "y[]") List<String> y, @RequestParam(value = "x[]") List<String> x, @RequestParam(value = "time[]") List<String> time, PlannerDTO dto) throws ParseException {
        PlannerDTO plannerDTO = plannerService.insertPlanner(dto);

        List<Date> days = planService.changeDateListForUpdate(date, time);
        List<Float> float_x = planService.changeFloatList(x);
        List<Float> float_y = planService.changeFloatList(y);
        List<Long> long_planNo = planService.changeLongList(planNo);

        List<PlanDTO> plans = planService.returnUpdatePlan(days, place, planIntro, float_x, float_y, long_planNo, plannerDTO.getPlannerNo());
        planService.updatePlans(plans, dto.getPlannerNo());

        return "planD?plannerNo=" + dto.getPlannerNo();
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