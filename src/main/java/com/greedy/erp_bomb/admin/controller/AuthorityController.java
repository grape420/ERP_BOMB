package com.greedy.erp_bomb.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.greedy.erp_bomb.admin.model.service.AuthorityService;
import com.greedy.erp_bomb.member.model.dto.MemberDTO;

@Controller
@RequestMapping("/admin")
public class AuthorityController {
	
	private AuthorityService authorityService;
	
	@Autowired
	public AuthorityController(AuthorityService authorityService) {
		this.authorityService = authorityService;
	}
	
	@GetMapping("/authority")
	public ModelAndView findAuthorityList(ModelAndView mv) {
		List<MemberDTO> memberList = authorityService.findAuthorityList();
		
		mv.addObject("memberList", memberList);
		mv.setViewName("admin/authority");
		return mv;
	}
	
//	@GetMapping(value = "authDetail", produces = "application/json; charset=UTF-8")
//	@ResponseBody
//	public MemberRoleDTO findAuthDetail(@RequestParam String detailName) {
//		System.out.println("너 이름 뭐야 : " + detailName);
//		
//		MemberRoleDTO member = authorityService.findAuthDetail(detailName);
//		
//		System.out.println("컨트롤러 : " + member);
//
//		return authorityService.findAuthDetail(detailName);
//	}
	
	

}
