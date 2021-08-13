package com.finalprj.doldolseo.controller.crew;

import com.finalprj.doldolseo.domain.crew.Crew;
import com.finalprj.doldolseo.dto.crew.CrewDTO;
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
import java.util.stream.Collectors;

@Controller
public class CrewController {

    @Autowired
    CrewServiceImpl service;

    @Autowired
    UploadFileUtil uploadFileUtil;

    @RequestMapping("/crewL")
    public String crewList(Model model,
                           @PageableDefault(size = 16, sort = "cDate", direction = Sort.Direction.DESC) Pageable pageable) throws Exception {
        Page<CrewDTO> crews = service.getCrewList(pageable);

        PagingUtil pagingUtil = new PagingUtil(10, crews);

        model.addAttribute("startBlockPage", pagingUtil.startBlockPage);
        model.addAttribute("endBlockPage", pagingUtil.endBlockPage);
        model.addAttribute("crews", crews);

        return "/crew/crewList";
    }

    @RequestMapping(value = "/crewI", method = RequestMethod.GET)
    public String crewInsertForm() throws Exception {
        return "/crew/crewInsert";
    }

    /* 크루 생성 */
    @RequestMapping(value = "/crewI", method = RequestMethod.POST)
    public String crewInsert(@RequestBody MultipartFile crewImageFile,
                             CrewDTO dto) throws Exception {
        //파일 저장 처리
        String crewImageName = uploadFileUtil.crewImgSave(crewImageFile, dto.getCrewName());

        //areaListValues(String[]) -> areaList(String)로 저장
        System.out.println(Arrays.toString(dto.getAreaListValues()));
        dto.setAreaList(String.join(",", dto.getAreaListValues()));

        dto.setCrewImage(crewImageName);
        service.insertCrew(dto);

        return "/crew/crewList";
    }

    /* 크루명 중복 체크 */
    @RequestMapping(value = "/crewI/check", method = RequestMethod.GET)
    @ResponseBody
    public boolean crewCheck(@RequestParam String crewName) throws Exception {

        return service.checkCrewName(crewName);
    }

    @RequestMapping("/crewD")
    public String crewDetail() throws Exception {
        return "/crew/crewDetail";
    }


    @RequestMapping("/crewM")
    public String crewManage() throws Exception {
        return "/crew/crewManage";
    }

    @RequestMapping("/crewJ")
    public String popupCrewJoin() throws Exception {
        return "/crew/popup_crewJoin";
    }

}
