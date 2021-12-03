package com.greedy.erp_bomb.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.greedy.erp_bomb.admin.model.service.AuthorityService;
import com.greedy.erp_bomb.member.model.dto.AuthorityDTO;
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
	public ModelAndView findmemberList(ModelAndView mv) {
		List<MemberDTO> memberList = authorityService.findMemberList();
		
		mv.addObject("memberList", memberList);
		mv.setViewName("admin/authority");
		return mv;
	}
	
	@GetMapping(value = "authDetail", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public MemberDTO findAuthDetail(@RequestParam String detailName) {
		MemberDTO member = authorityService.findMemberDetail(detailName);
		
		MemberDTO detailMember = new MemberDTO();
		detailMember.setName(member.getName());

		return detailMember;
	}
	
	@GetMapping(value = "allAuth", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<AuthorityDTO> findAllAuth() {
		return authorityService.findAllAuth();
	}
	
	@PostMapping("updateAuth")
	public String updateAuth(@RequestParam String name, HttpServletRequest request) {
		authorityService.updateAuth(request.getParameterValues("role"), name);
		
		return "redirect:/admin/authority";
	}
	

}
