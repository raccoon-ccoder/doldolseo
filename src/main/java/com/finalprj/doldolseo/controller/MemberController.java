package com.finalprj.doldolseo.controller;

import com.finalprj.doldolseo.dto.MemberDTO;
import com.finalprj.doldolseo.domain.Member;
import com.finalprj.doldolseo.service.MemberService;

import com.finalprj.doldolseo.util.UploadProfileUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/*
 * 멤버 Controller
 *
 * @Author 백정연
 * @Date 2021/08/03
 */

@Controller
public class MemberController {

    @Autowired
    private MemberService service;

    @Autowired
    private UploadProfileUtil profileUtil;

    // 추가 코드
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping("/register")
    public String register(@RequestParam(value = "memberimg") MultipartFile file, MemberDTO memberDTO, Model model, HttpServletRequest request) throws Exception{
        String profileImg = "sample.png";
        if(!(file.isEmpty())){
            profileImg = profileUtil.uploadProfile(file, memberDTO);
        }
        memberDTO.setMember_img(profileImg);
        // 추가 코드
        String rawPassword = memberDTO.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        memberDTO.setPassword(encPassword);
        // //추가 코드

        MemberDTO member = service.join(memberDTO);

        request.setAttribute("id",member.getId());
        request.setAttribute("nickname",member.getNickname());
        return "/member/memberJoinResult";
    }

    @RequestMapping(value="/member/checkId")
    @ResponseBody
    public Map<Object, Object> checkId(@RequestBody String id) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        int result = service.checkId(id);
        map.put("result", result);
        return map;
    }

    @RequestMapping(value="/member/checkNickname")
    @ResponseBody
    public Map<Object, Object> checkNickname(@RequestBody String nickname) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        int result = service.checkNickname(nickname);
        map.put("result", result);
        return map;
    }

    @RequestMapping("/memberJ")
    public String memberJoin() throws Exception{
        return "/member/memberJoin";
    }

    @RequestMapping("/memberJR")
    public String memberJoinResult() throws Exception{
        return "/member/memberJoinResult";
    }

    @RequestMapping("/memberP")
    public String memberPolicy() throws Exception{
        return "/member/memberPolicy";
    }

    @RequestMapping("/memberR")
    public String memberRule() throws Exception{
        return "/member/memberRule";
    }
}
