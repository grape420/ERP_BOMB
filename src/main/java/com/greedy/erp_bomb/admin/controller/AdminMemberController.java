package com.greedy.erp_bomb.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.greedy.erp_bomb.admin.model.service.AdminMemberService;
import com.greedy.erp_bomb.inventory.model.dto.CompanyDTO;
import com.greedy.erp_bomb.member.model.dto.DeptDTO;
import com.greedy.erp_bomb.member.model.dto.MemberDTO;
import com.greedy.erp_bomb.member.model.dto.RankDTO;

@Controller
@RequestMapping("/admin")
public class AdminMemberController {
	
	private AdminMemberService adminMemberService;
	
	@Autowired
	public AdminMemberController(AdminMemberService adminMemberService) {
		this.adminMemberService = adminMemberService;
	}
	
	@GetMapping("/member")
	public ModelAndView findMemberList(ModelAndView mv) {
		List<MemberDTO> memberList = adminMemberService.findMemberList();
		
		mv.addObject("memberList", memberList);
		mv.setViewName("admin/member");
		return mv;
	}
	
	@GetMapping(value = "companyCode", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<CompanyDTO> findCompanyList() {
		return adminMemberService.findCompanyList();
	}
	
	@GetMapping(value = "deptCode", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<DeptDTO> findDeptCodeList() {
		return adminMemberService.findDeptCodeList();
	}
	
	@GetMapping(value = "rankCode", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<RankDTO> findRankCodeList() {
		return adminMemberService.findRankCodeList();
	}
	
	@GetMapping("/registMember")
	public ModelAndView registPage(ModelAndView mv) {
		
		mv.setViewName("admin/registMember");
		return mv;
	}
	

}
