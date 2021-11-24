package com.greedy.erp_bomb.salary.controller;

import java.security.Principal;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public ModelAndView findAllMySalary(Principal principal, ModelAndView mv) {
		UserImpl user = (UserImpl)((Authentication)principal).getPrincipal();
		
		System.out.println("==========SalaryController===========");
		List<SalaryDTO> salaryList = salaryService.findAllMySalary(user.getName());
		List<SalaryDTO> allSalaryList = salaryService.findAllSalary();

		mv.addObject("salaryList", salaryList);

//		로그인 한 사람의 권한 정보 받아오는 법 ? 
//		mv.addObject("user", user);
		mv.addObject("allSalaryList", allSalaryList);
		
		mv.setViewName("/salary/salary");
		
		return mv;
	}

	/* 신규 급여 내역 추가 */
	@PostMapping("regist")
	public ModelAndView registMenu(ModelAndView mv, SalaryDTO newSalary, RedirectAttributes rttr, Locale locale) {
		
		salaryService.registNewSalary(newSalary);

		System.out.println("new " + newSalary);
		
		rttr.addFlashAttribute("registSuccessMessage", "급여 등록에 성공하셨습니다");
		mv.setViewName("redirect:/salary/salary");
		
		return mv;
	}

}

