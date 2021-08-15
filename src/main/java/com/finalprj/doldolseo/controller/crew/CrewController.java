package com.finalprj.doldolseo.controller.crew;

import com.finalprj.doldolseo.dto.crew.CrewDTO;
import com.finalprj.doldolseo.dto.crew.CrewMemberDTO;
import com.finalprj.doldolseo.service.impl.crew.CrewMemberServiceImpl;
import com.finalprj.doldolseo.service.impl.crew.CrewServiceImpl;
import com.finalprj.doldolseo.util.PagingUtil;
import com.finalprj.doldolseo.util.UploadFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
                             CrewDTO dto) throws Exception {
        //크루 이미지 저장 처리
        String crewImageName = uploadFileUtil.crewImgSave(crewImageFile, dto);

        //areaListValues(String[]) -> areaList(String)로 저장
        System.out.println(Arrays.toString(dto.getAreaListValues()));
        dto.setAreaList(String.join(",", dto.getAreaListValues()));

        dto.setCrewImage(crewImageName);
        crewService.insertCrew(dto);

        return "/crew/crewList";
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

        model.addAttribute("crew", crew);
        return "/crew/crewDetail";
    }

    /* 크루관리 폼 */
    @RequestMapping("/crewM")
    public String crewManage(Model model,
                             @RequestParam Long crewNo) throws Exception {
        CrewDTO crew = crewService.getCrew(crewNo);
        List<CrewMemberDTO> crewMembers = crewMemberService.getCrewMembers(crewNo);
        List<CrewMemberDTO> watingMembers = crewMemberService.getWatingMember(crewNo);

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

        crewService.updateCrew(dto,action);

//        return "redirect:/crewM?crewNo="+dto.getCrewNo();
    }

    /* 크루 가입 양식 수정 폼 */
    @RequestMapping(value = "/crewM/editJoin", method = RequestMethod.GET)
    public String getEditJoinForm(Model model,CrewDTO dto) throws Exception {


        model.addAttribute("crew",dto);
        return "/crew/popup_crewEditJoin";
    }

}
