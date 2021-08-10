package com.finalprj.doldolseo.controller;

import com.finalprj.doldolseo.dto.MemberDTO;
import com.finalprj.doldolseo.dto.PlannerDTO;
import com.finalprj.doldolseo.entity.Plan;
import com.finalprj.doldolseo.entity.Planner;
import com.finalprj.doldolseo.impl.PlanServiceImpl;
import com.finalprj.doldolseo.impl.PlannerServiceImpl;
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

@Controller
public class PlannerController {

    @Autowired
    private PlannerServiceImpl plannerService;

    @Autowired
    private PlanServiceImpl planService;

    @RequestMapping("/planD")
    public String planDetail(PlannerDTO dto,Model model) throws Exception{
        Planner planner = plannerService.selectPlanner(dto.getPlannerNo());
        List<Date> dates = planService.getDiffDays(planner.getFDate(),planner.getLDate());
        List<Plan> plans = planService.selectPlan(dto.getPlannerNo());
        model.addAttribute("planner",planner);
        model.addAttribute("dates",dates);
        model.addAttribute("plans",plans);

        return "/mypage/plan/planDetail";
    }


    @RequestMapping("/planL")
    public String planList(MemberDTO dto,Model model) throws Exception{
        List<Planner> planners = plannerService.selectPlanners(dto.getId());
        List<Plan> plans = planService.joinPlans(planners);
        model.addAttribute("planners",planners);
        model.addAttribute("plans",plans);

        return "/mypage/plan/planList";
    }

    @RequestMapping(value="/plannerInsert", method = RequestMethod.POST)
    @ResponseBody
    public String planInsertTest(@RequestParam(value = "date[]") List<String> date, @RequestParam(value = "place[]") List<String> place, @RequestParam(value = "plan_intro[]") List<String> plan_intro, @RequestParam(value = "y[]") List<String> y, @RequestParam(value = "x[]") List<String> x, @RequestParam(value = "time[]") List<String> time, PlannerDTO dto) throws ParseException {
        Planner planner = plannerService.insertPlanner(dto);

        List<Date> days = planService.changeDateList(date, time);
        List<Float> float_x = planService.changeFloatList(x);
        List<Float> float_y = planService.changeFloatList(y);

        List<Plan> plans = planService.returnPlan(days, place, plan_intro, float_x, float_y, planner.getPlannerNo());
        planService.insertPlan(plans);
        return "planL?id=" + dto.getId();
    }

    @RequestMapping(value="/plannerUpdate", method = RequestMethod.POST)
    @ResponseBody
    public String plannerUpdate(@RequestParam(value = "planNo[]") List<String> planNo,@RequestParam(value = "date[]") List<String> date, @RequestParam(value = "place[]") List<String> place, @RequestParam(value = "plan_intro[]") List<String> plan_intro, @RequestParam(value = "y[]") List<String> y, @RequestParam(value = "x[]") List<String> x, @RequestParam(value = "time[]") List<String> time, PlannerDTO dto) throws ParseException {
        Planner planner = plannerService.insertPlanner(dto);

        List<Date> days = planService.changeDateListForUpdate(date, time);
        List<Float> float_x = planService.changeFloatList(x);
        List<Float> float_y = planService.changeFloatList(y);
        List<Long> long_planNo = planService.changeLongList(planNo);

        List<Plan> plans = planService.returnUpdatePlan(days, place, plan_intro, float_x, float_y,long_planNo, planner.getPlannerNo());
        planService.updatePlans(plans, dto.getPlannerNo());
        planService.insertPlan(plans);

        return "planD?plannerNo=" + dto.getPlannerNo();
    }

    @RequestMapping("plannerDelete")
    public String deletePlanner(PlannerDTO dto,Model model){
        planService.deletePlans(dto.getPlannerNo());
        plannerService.deletePlanner(dto.getPlannerNo());

        return "redirect:/planL?id=" + dto.getId();
    }

    @RequestMapping("/goPlanI")
    public String goIsertPage(PlannerDTO dto,Model model) throws Exception{
        List<Date> days = planService.getDiffDays(dto.getFDate(), dto.getLDate());
        model.addAttribute("days", days);
        model.addAttribute("planner_user",dto);

        return "/mypage/plan/planInsert";
    }

    @RequestMapping("/goPlanU")
    public String goUpdatePage(PlannerDTO dto,Model model) throws Exception{
        List<Date> days = planService.getDiffDays(dto.getFDate(), dto.getLDate());
        List<Plan> plans = planService.selectPlan(dto.getPlannerNo());
        model.addAttribute("days", days);
        model.addAttribute("planner_user",dto);
        model.addAttribute("plans", plans);

        return "/mypage/plan/planUpdate";
    }
}
