package com.greedy.erp_bomb.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.greedy.erp_bomb.ea.model.service.EaService;
import com.greedy.erp_bomb.member.model.dto.MemberDTO;
import com.greedy.erp_bomb.member.model.dto.UserImpl;

@Controller
public class MainController {
	
	private EaService eaService;
	
	@Autowired
	public MainController(EaService eaService) {
		this.eaService = eaService;
	}
	
	@GetMapping(value = {"/", "main"})
	public String main() {
		return "member/login";
	}
	
	@PostMapping(value = "/main/main")
	public ModelAndView mainMain(@AuthenticationPrincipal UserImpl user, ModelAndView mv) {
		
		String userName = user.getName();
		
		List<MemberDTO> memberList = eaService.findMemberList();
		
		for(int i = 0 ; i < memberList.size() ; i++) {
			if(userName.equals(memberList.get(i).getName())) {
				memberList.remove(i);
			}
		}
		
		mv.addObject("memberList", memberList);
		mv.addObject("userName", userName);
		mv.setViewName("main/main");
		
		return mv;
	}
	
	@PostMapping(value = "/")
	public String redirectMain() {
		return "redirect:/";
	}

}
