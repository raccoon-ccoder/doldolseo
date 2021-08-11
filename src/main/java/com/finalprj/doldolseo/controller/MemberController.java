package com.finalprj.doldolseo.controller;

import com.finalprj.doldolseo.dto.MemberDTO;
import com.finalprj.doldolseo.domain.Member;
import com.finalprj.doldolseo.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping("/register")
    public String register(MemberDTO memberDTO,Model model) throws Exception{
        Member member = service.join(memberDTO);
        model.addAttribute("member",member);
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
