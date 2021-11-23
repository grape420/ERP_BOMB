package com.greedy.erp_bomb.salary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greedy.erp_bomb.salary.model.dto.SalaryDTO;
import com.greedy.erp_bomb.salary.model.service.SalaryService;

public class SalaryController {

	@Controller
	@RequestMapping("/salary")
	public class MemberController {
		
		private SalaryService salaryService;
		private ObjectMapper objectMapper;
		
		@Autowired
		public MemberController(SalaryService salaryService, ObjectMapper objectMapper) {
			this.salaryService = salaryService;
			this.objectMapper = objectMapper;
		}	
		
		/* 본인의 급여 목록 조회 */
		@GetMapping("/salary")
		public ModelAndView findAllMySalary(ModelAndView mv) {
			List<SalaryDTO> salaryList = salaryService.findAllMySalary();
			
			mv.addObject("salaryList", salaryList);
			mv.setViewName("/salary/salary");
			
			return mv;
		}
	}
}
