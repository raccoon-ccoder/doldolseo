package com.finalprj.doldolseo.controller.crew;

import com.finalprj.doldolseo.dto.MemberDTO;
import com.finalprj.doldolseo.dto.crew.CrewDTO;
import com.finalprj.doldolseo.dto.crew.CrewMemberDTO;
import com.finalprj.doldolseo.service.impl.MemberServiceImpl;
import com.finalprj.doldolseo.service.impl.crew.CrewMemberServiceImpl;
import com.finalprj.doldolseo.service.impl.crew.CrewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@Controller
public class CrewMemberController {

    @Autowired
    CrewMemberServiceImpl crewMemberService;
    @Autowired
    CrewServiceImpl crewService;
    @Autowired
    MemberServiceImpl memberService;


    @RequestMapping(value = "/crewJ", method = RequestMethod.GET)
    public String getCrewJoinForm(Model model,
                                  @RequestParam Long crewNo) {
        CrewDTO crew = crewService.getCrew(crewNo);

        model.addAttribute("crew", crew);
        return "/crew/popup_crewJoin";
    }

    //크루 가입
    @RequestMapping(value = "/crewJ", method = RequestMethod.POST)
    public String joinCrew(CrewMemberDTO dto) {

        //크루장 가입 방지
        if (crewService.isCrewLeader(dto.getCrewNo(), dto.getId())) {
            System.out.println("이미 해당 크루의 크루장 입니다.");
        } else {
            //크루 재가입 방지
            if (crewMemberService.hasThisCrewMember(dto.getCrewNo(), dto.getId())) {
                System.out.println("이미 가입된 크루원 입니다.");
            } else {
                //크루원 등록
                crewMemberService.insertCrewMember(dto);
            }
        }

        return "redirect:/crewL";
    }

    //크루 가입 승인
    @RequestMapping(value = "/crewJ/agree", method = RequestMethod.POST)
    public String agreeCrewJoin(@RequestBody Map<String, Long> regNoMap) {

        crewMemberService.updateCrew(regNoMap.get("regNo"));
        return "redirect:/crewL";
    }

    //크루 가입 거절 및 강퇴
    @RequestMapping(value = "/crewJ/deny", method = RequestMethod.POST)
    public String denyCrewJoinOrKick(@RequestBody Map<String, Long> regNoMap) {

        crewMemberService.deleteCrew(regNoMap.get("regNo"));
        return "redirect:/crewL";
    }

    //크루장 위임
    @RequestMapping(value = "/crewJ/give", method = RequestMethod.POST)
    public String giveMaster(@RequestBody CrewMemberDTO dto, HttpServletRequest request) throws IOException {


        CrewMemberDTO crewMemberDTO = crewMemberService.getCrewMember(dto.getRegNo());

        crewMemberService.deleteCrew(dto.getRegNo());//크루원 삭제

        MemberDTO memberDTO = memberService.selectMember(dto.getId());

        if (memberDTO.getCrleader() == 'y') {
            System.out.println("해당 멤버는 이미 크루장 입니다.");
        } else {
            memberDTO.setCrleader('y');
            memberService.updateMemberSecurity(memberDTO, request.getSession());

            crewService.updateCrewMaster(crewMemberDTO.getCrewNo(), crewMemberDTO.getId());
        }

        return "redirect:/crewL";
    }


}
