package com.finalprj.doldolseo.controller.crew;

import com.finalprj.doldolseo.domain.Member;
import com.finalprj.doldolseo.dto.MemberDTO;
import com.finalprj.doldolseo.dto.crew.CrewDTO;
import com.finalprj.doldolseo.dto.crew.CrewMemberDTO;
import com.finalprj.doldolseo.service.impl.MemberServiceImpl;
import com.finalprj.doldolseo.service.impl.crew.CrewMemberServiceImpl;
import com.finalprj.doldolseo.service.impl.crew.CrewServiceImpl;
import com.finalprj.doldolseo.util.PagingUtil;
import com.finalprj.doldolseo.util.UploadFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@Controller
public class CrewController {

    @Autowired
    CrewServiceImpl crewService;
    @Autowired
    CrewMemberServiceImpl crewMemberService;

    @Autowired
    UploadFileUtil uploadFileUtil;

    //추가코드
    @Autowired
    MemberServiceImpl memberService;

    /* 크루 목록 보기 */
    @RequestMapping("/crewL")
    public String crewList(Model model,
                           @PageableDefault(size = 16, sort = "cDate", direction = Sort.Direction.DESC) Pageable pageable) throws Exception {
        Page<CrewDTO> crews = crewService.getCrewList(pageable);

        PagingUtil pagingUtil = new PagingUtil(10, crews);

        model.addAttribute("startBlockPage", pagingUtil.startBlockPage);
        model.addAttribute("endBlockPage", pagingUtil.endBlockPage);
        model.addAttribute("crews", crews);

        return "/crew/crewList";
    }

    /* 크루 생성 폼 */
    @RequestMapping(value = "/crewI", method = RequestMethod.GET)
    public String crewInsertForm() throws Exception {
        return "/crew/crewInsert";
    }

    /* 크루 생성 */
    @RequestMapping(value = "/crewI", method = RequestMethod.POST)
    public String crewInsert(@RequestBody(required = false) MultipartFile crewImageFile,
                             HttpSession session,
                             CrewDTO dto) throws Exception {
        //크루 이미지 저장 처리
        String crewImageName = uploadFileUtil.crewImgSave(crewImageFile, dto);

        //areaListValues(String[]) -> areaList(String)로 저장
        System.out.println(Arrays.toString(dto.getAreaListValues()));
        dto.setAreaList(String.join(",", dto.getAreaListValues()));

        dto.setCrewImage(crewImageName);

        // 추가코드
        memberService.setMemberToCrleader(dto.getMember().getId(), session); //해당 멤버 크루장으로
        Member member = memberService.selectMemberEntity(dto.getMember());
        session.setAttribute("member",memberService.entityToDto(member));
        //update추가
//        memberService.updateMemberSecurity(, session);
        dto.setMember(member);
        // end of 추가코드

        crewService.insertCrew(dto);

        return "redirect:/crewL";
    }

    /* 크루명 중복 체크 */
    @RequestMapping(value = "/crewI/check", method = RequestMethod.GET)
    @ResponseBody
    public boolean crewCheck(@RequestParam String crewName) throws Exception {

        return crewService.checkCrewName(crewName);
    }

    /* 크루 상세 보기 */
    @RequestMapping(value = "/crewD", method = RequestMethod.GET)
    public String crewDetail(Model model,
                             @RequestParam Long crewNo) throws Exception {
        CrewDTO crew = crewService.getCrew(crewNo);
        List<CrewMemberDTO> crewMembers = crewMemberService.getCrewMembers(crewNo);

        model.addAttribute("crewMembers", crewMembers);
        model.addAttribute("crew", crew);
        return "/crew/crewDetail";
    }

    /* 크루관리 폼 */
    @PreAuthorize("isAuthenticated() and hasAuthority('y')")
    @RequestMapping("/crewM")
    public String crewManage(Model model,
                             HttpSession session) throws Exception {

        MemberDTO member = (MemberDTO) session.getAttribute("member");
        CrewDTO crew = crewService.getCrewById(member.getId());


        List<CrewMemberDTO> crewMembers = crewMemberService.getCrewMembers(crew.getCrewNo());
        List<CrewMemberDTO> watingMembers = crewMemberService.getWatingMember(crew.getCrewNo());

        model.addAttribute("crew", crew);
        model.addAttribute("crewMembers", crewMembers);
        model.addAttribute("watingMembers", watingMembers);

        return "/crew/crewManage";
    }

    /* 크루관리 - 수정 */
    @RequestMapping(value = "/crewM/edit/{action}", method = RequestMethod.POST)
    public void crewManageEdit(@PathVariable("action") String action,
                               CrewDTO dto,
                               MultipartFile crewImageFile) throws Exception {

        if (action.equals("img")) {
            CrewDTO crew = crewService.getCrew(dto.getCrewNo());
            String imgFileName = uploadFileUtil.crewImgSave(crewImageFile, crew);
            System.out.println(imgFileName);
            dto.setCrewImage(imgFileName);
        }

        crewService.updateCrew(dto, action);

//        return "redirect:/crewM?crewNo="+dto.getCrewNo();
    }

    /* 크루 가입 양식 수정 폼 */
    @RequestMapping(value = "/crewM/editJoin", method = RequestMethod.GET)
    public String getEditJoinForm(Model model, CrewDTO dto) throws Exception {


        model.addAttribute("crew", dto);
        return "/crew/popup_crewEditJoin";
    }

}
