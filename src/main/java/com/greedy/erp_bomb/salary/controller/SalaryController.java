package com.greedy.erp_bomb.salary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

public class SalaryController {

	@Controller
	@RequestMapping("/salary")
	public class MemberController {
			
		/* 급여관리 */
		@GetMapping("/salary")
		public ModelAndView salaryManagement(ModelAndView mv) {
			
			mv.setViewName("/salary/salary");
			
			return mv;
		}
	}
}
