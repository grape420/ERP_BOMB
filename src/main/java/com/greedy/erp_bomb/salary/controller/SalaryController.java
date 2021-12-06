package com.greedy.erp_bomb.salary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.greedy.erp_bomb.member.model.dto.MemberDTO;
import com.greedy.erp_bomb.member.model.dto.UserImpl;
import com.greedy.erp_bomb.salary.model.dto.SalaryDTO;
import com.greedy.erp_bomb.salary.model.service.SalaryService;

@Controller
@RequestMapping("/salary")
public class SalaryController {
		
	private SalaryService salaryService;
	
	@Autowired
	public SalaryController(SalaryService salaryService) {
		this.salaryService = salaryService;
	}	
	
	/* 본인 및 모든 급여 목록 조회 */
	@GetMapping("/salary")
	public ModelAndView findAllMySalary(@AuthenticationPrincipal UserImpl user, ModelAndView mv) {
		List<SalaryDTO> salaryList = salaryService.findAllMySalary(user.getName());
		List<MemberDTO> memberList = salaryService.findMemberList();
		
		for (SalaryDTO salaryDTO : salaryList) {
			if (salaryDTO.getBonus() == null) {
				salaryDTO.setBonus(0);
			} if (salaryDTO.getRegularPay() == null) {
				salaryDTO.setRegularPay(0);
			}
			
			System.out.println(salaryDTO);
		}
		
		mv.addObject("salaryList", salaryList);
		mv.addObject("memberList", memberList);
		
		mv.setViewName("/salary/salary");
		
		return mv;
	}
}

