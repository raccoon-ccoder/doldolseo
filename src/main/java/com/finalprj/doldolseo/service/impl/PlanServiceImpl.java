package com.finalprj.doldolseo.service.impl;

/*
 * 플랜 Service 구현 클래스
 *
 * @Author 백정연
 * @Date 2021/08/06
 */

import com.finalprj.doldolseo.domain.Planner;
import com.finalprj.doldolseo.dto.PlanDTO;
import com.finalprj.doldolseo.dto.PlannerDTO;
import com.finalprj.doldolseo.repository.PlanRepository;
import com.finalprj.doldolseo.domain.Plan;
import com.finalprj.doldolseo.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PlanServiceImpl implements PlanService {
    @Autowired
    private PlanRepository repository;

    public void insertPlan(List<PlanDTO> planDTOS){
        for(PlanDTO dto : planDTOS){
            repository.save(dtoToEntity(dto));
        }
    }

    /* 플래너 번호에 따른 플랜들을 List 타입으로 반환하는 메소드 */
    @Override
    public List<PlanDTO> selectPlan(Long plannerNo) {
        List<Plan> plans = repository.findAllByPlannerPlannerNoOrderByPlanNo(plannerNo);
        List<PlanDTO> planDTOS = new ArrayList<PlanDTO>();
        for(Plan plan : plans){
            planDTOS.add(entityToDto(plan));
        }
        return planDTOS;
    }

    /* 플래너의 첫번째 날짜 일정들을 반환하는 메소드 (planList.jsp로 이동시 사용) */
    @Override
    public List<PlanDTO> selectPlans(PlannerDTO planner) {
        Date now = planner.getFDate();
        Date start = planner.getFDate();
        Date last = new Date(now.getYear(), now.getMonth(), now.getDate(), 23, 59,59);

        List<Plan> result = repository.findAllByPlannerPlannerNoAndDayBetweenOrderByPlanNo(planner.getPlannerNo(), start, last);
        List<PlanDTO> dto = new ArrayList<PlanDTO>();
        for(Plan plan : result){
            dto.add(entityToDto(plan));
        }
        return dto;
    }

    /* 사용자가 작성한 각 플래너마다 플랜들을 구한 후 join하여 1개의 List 타입으로 반환하는 메소드  */
    @Override
    public List<PlanDTO> joinPlans(List<PlannerDTO> planners) {
        List<PlanDTO> allPlans = new ArrayList<PlanDTO>();
        for(int i=0;i<planners.size();i++){
            List<PlanDTO> plans = selectPlans(planners.get(i));
            allPlans.addAll(plans);
        }
        return allPlans;
    }

    @Override
    public void deletePlans(Long plannerNo) {
        repository.deleteAllByPlannerPlannerNo(plannerNo);
    }

    /* plnaInsert.jsp에서 전달받은 값들을 List<Plan>으로 반환 */
    public List<PlanDTO> returnPlan(List<Date> days, List<String> place, List<String> place_intro, List<Float> X, List<Float> Y, Long planner_no){
        List<PlanDTO> plans = new ArrayList<PlanDTO>();

        for(int i=0;i<days.size();i++){
            if((place.size()==1) && place_intro.size() == 0){    // 여행 날짜가 1일, 플랜이 1개이며 메모가 없는 플래너일경우
                PlanDTO plan = PlanDTO.builder()
                        .planner(Planner.builder().plannerNo(planner_no).build())
                        .day(days.get(i))
                        .name(place.get(i))
                        .intro(null)
                        .x(X.get(i))
                        .y(Y.get(i))
                        .build();
                plans.add(plan);
            }else{
                PlanDTO plan = PlanDTO.builder()
                        .planner(Planner.builder().plannerNo(planner_no).build())
                        .day(days.get(i))
                        .name(place.get(i))
                        .intro(place_intro.get(i))
                        .x(X.get(i))
                        .y(Y.get(i))
                        .build();
                plans.add(plan);
            }

        }
        return  plans;
    }

    /* planUpdate.jsp에서 전달받은 값들을 List<Plan>으로 반환 */
    public List<PlanDTO> returnUpdatePlan(List<Date> days, List<String> place, List<String> plan_intro, List<Float> X, List<Float> Y,List<Long> planNo, Long planner_no){
        List<PlanDTO> plans = new ArrayList<PlanDTO>();

        for(int i=0;i<days.size();i++){
            if((place.size()==1) && plan_intro.size() == 0){     // 여행 날짜가 1일, 플랜이 1개이며 메모가 없는 플래너일경우
                PlanDTO plan = PlanDTO.builder()
                        .planner(Planner.builder().plannerNo(planner_no).build())
                        .day(days.get(i))
                        .name(place.get(i))
                        .intro(null)
                        .x(X.get(i))
                        .y(Y.get(i))
                        .build();
                plans.add(plan);
            }else{
                PlanDTO plan = PlanDTO.builder()
                        .planner(Planner.builder().plannerNo(planner_no).build())
                        .planNo(planNo.get(i))
                        .day(days.get(i))
                        .name(place.get(i))
                        .intro(plan_intro.get(i))
                        .x(X.get(i))
                        .y(Y.get(i))
                        .build();
                plans.add(plan);
            }
        }

        return  plans;
    }

    public void updatePlans(List<PlanDTO> updatedPlans, Long plannerNo){
        List<PlanDTO> origin_plans = selectPlan(plannerNo);

        // 사용자가 삭제한 플랜들 DB에서 삭제
        for(PlanDTO oldPlan : origin_plans){
            boolean isContained = false;

            for(PlanDTO newPlan : updatedPlans){
                if(oldPlan.getPlanNo() == newPlan.getPlanNo()){
                    isContained = true;
                    break;
                }
            }

            if(isContained == false){
                repository.deleteByPlanNo(oldPlan.getPlanNo());
            }
        }

        // 수정된 플랜들 DB 반영
        insertPlan(updatedPlans);
    }

    public int getDiffDayCount(Date fromDate, Date toDate){
        return (int)((toDate.getTime() - fromDate.getTime()) / 1000 / 60 / 60 / 24);
    }

    public List<Date> getDiffDays(Date fromDate, Date toDate){
        Calendar cal = Calendar.getInstance();
        cal.setTime(fromDate);
        int count = getDiffDayCount(fromDate,toDate);
        // 시작일부터
        cal.add(Calendar.DATE, -1);
        // 데이터 저장
        List result = new ArrayList();
        for(int i = 0;i<=count;i++){
            cal.add(Calendar.DATE, 1);
            result.add(cal.getTime());
        }
        return result;
    }

    public List<Date> changeDateList(List<String> dates, List<String> times) throws ParseException {
        List<Date> result = new ArrayList<Date>();

        for(int i=0;i<dates.size();i++){
            SimpleDateFormat recvSimpleFormat = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
            SimpleDateFormat tranSimpleFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
            Date dateDate = recvSimpleFormat.parse(dates.get(i));
            String stringDate = tranSimpleFormat.format(dateDate);

            String stringDay = stringDate + " " + times.get(i);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

            Date dateDay = format.parse(stringDay);
            result.add(dateDay);
        }

        return result;
    }

    public List<Date> changeDateListForUpdate(List<String> dates, List<String> times) throws ParseException {
        List<Date> result = new ArrayList<Date>();

        for(int i=0;i<dates.size();i++){
            String stringDay = dates.get(i) + " " + times.get(i);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

            Date dateDay = format.parse(stringDay);
            result.add(dateDay);
        }

        return result;
    }

    public List<Float> changeFloatList(List<String> data){
        List<Float> result = new ArrayList<Float>();
        for(int i=0;i<data.size();i++){
            Float floatNum = Float.parseFloat(data.get(i));
            result.add(floatNum);
        }
        return result;
    }

    public List<Long> changeLongList(List<String> data){
        List<Long> result = new ArrayList<Long>();
        for(int i=0;i<data.size();i++){
            if(!(data.get(i) == "")){
                Long longNum = Long.parseLong(data.get(i));
                result.add(longNum);
            }else{
                result.add(null);
            }
        }
        return result;
    }
}