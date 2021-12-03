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
		MemberDTO member = authorityService.findAuthDetail(detailName);
		MemberDTO detailMember = new MemberDTO();
		detailMember.setName(member.getName());

		return detailMember;
	}
	
	@PostMapping("updateAuth")
	public String updateAuth(@RequestParam String name, HttpServletRequest request) {
		/* 일반회원에서 일반 회원 체크 - 만들지마 */
		/* 일반회원에서 일반 회원, 관리자 체크 - INSERT */
		/* 일반회원에서 관리자 체크 - INSERT */
		
		/* 관리자에서 일반 회원 체크 - DELETE */
		/* 관리자에서 일반 회원, 관리자 체크 - 만들지마 */
		/* 관리자에서 관리자 체크 - 만들지마 */
		authorityService.updateAuth(request.getParameterValues("role"), name);
		
		return "redirect:/admin/authority";
	}
	

}
