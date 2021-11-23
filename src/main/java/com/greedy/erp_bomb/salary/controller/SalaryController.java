package com.greedy.erp_bomb.salary.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
		
	/* 본인의 급여 목록 조회 */
	@GetMapping("/salary")
	public ModelAndView findAllMySalary(Principal principal, ModelAndView mv) {
		String userName = ((UserImpl)((Authentication)principal).getPrincipal()).getName();

		System.out.println("==========SalaryController===========");
		System.out.println("user : " + (UserImpl)((Authentication)principal).getPrincipal());
		List<SalaryDTO> salaryList = salaryService.findAllMySalary(userName);
		
//		mv.addObject("user", )
		mv.addObject("salaryList", salaryList);
		mv.setViewName("/salary/salary");
		
		return mv;
	}
}

