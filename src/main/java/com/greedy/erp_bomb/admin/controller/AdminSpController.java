package com.greedy.erp_bomb.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.greedy.erp_bomb.admin.model.service.AdminSpService;
import com.greedy.erp_bomb.member.model.dto.MemberDTO;
import com.greedy.erp_bomb.sp.model.dto.SPDTO;

@Controller
@RequestMapping("/admin")
public class AdminSpController {
	
	private AdminSpService adminSpService;
	
	@Autowired
	public AdminSpController(AdminSpService adminSpService) {
		this.adminSpService = adminSpService;
	}
	
	@GetMapping("/sp")
	public ModelAndView findInvenList(ModelAndView mv) {
		
		List<MemberDTO> member = adminSpService.findEntryMember();
		
		List<SPDTO> spList = adminSpService.findSpList();
		
		mv.addObject("entYn", member);
		mv.addObject("spList", spList);
		
		mv.setViewName("admin/sp");
		return mv;
	}
	
	@PostMapping("regSp")
	public ModelAndView registSpList(ModelAndView mv, @RequestParam String name
									, @RequestParam Integer serverancePay, @RequestParam Integer empYear) {
		MemberDTO member = adminSpService.findMemberInfo(name);
		
		SPDTO sp = new SPDTO();
		sp.setServerancePay(serverancePay);
		sp.setEmpYear(empYear);
		sp.setMember(member);
		
		adminSpService.newRegistSp(sp);
		
		mv.setViewName("redirect:/admin/sp");
		
		return mv;
	}
	
	@PostMapping("updateSp")
	public ModelAndView UpdateSpList(ModelAndView mv, @RequestParam String name, @RequestParam Integer serverancePay, @RequestParam Integer empYear) {
		
		MemberDTO member = new MemberDTO();
		member = adminSpService.findMemberInfo(name);
		
		SPDTO sp2 = new SPDTO();
		sp2.setEmpYear(empYear);
		sp2.setMember(member);
		sp2.setServerancePay(serverancePay);
		
		adminSpService.updateSp(sp2);
		
		System.out.println("에러 확인??");
		
		mv.setViewName("redirect:/admin/sp");
		
		return mv;
	}

}
