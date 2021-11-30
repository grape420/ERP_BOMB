package com.greedy.erp_bomb.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public AdminMemberController(AdminMemberService adminMemberService, PasswordEncoder passwordEncoder) {
		this.adminMemberService = adminMemberService;
		this.passwordEncoder = passwordEncoder;
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
	
	@GetMapping(value = "nameCheck", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Boolean nameDoubleCheck(@RequestParam String value, HttpServletResponse response) {
		
		MemberDTO member = adminMemberService.selectMemberByName(value);
		
		if (member == null) {		// DB에 중복 값 없음
			return false;
		} else {					// DB에 중복 값 있음
			return true;
		}
	}
	
	@PostMapping("/signup")
	public ModelAndView registMember(ModelAndView mv, @RequestParam int companyCode, @RequestParam int deptCode, @RequestParam int rankCode, @ModelAttribute MemberDTO member) {
		String encodePassword = passwordEncoder.encode(member.getPwd());
		
		
		CompanyDTO com = new CompanyDTO();
		com.setSerialNo(companyCode);
		
		DeptDTO dept = new DeptDTO();
		dept.setNo(deptCode);
		
		RankDTO rank = new RankDTO();
		rank.setNo(rankCode);
		
		member.setCompany(com);
		member.setDept(dept);
		member.setRank(rank);
		member.setPwd(encodePassword);
		
		adminMemberService.registMember(member);
		
		mv.setViewName("redirect:/admin/member");
		
		return mv;
	}
	
	@GetMapping(value = "memDetail", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public MemberDTO memDetail(@RequestParam String detailName) {
		return adminMemberService.memDetail(detailName);
	}

}
