package com.greedy.erp_bomb.salary.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.greedy.erp_bomb.member.model.dto.MemberDTO;
import com.greedy.erp_bomb.member.model.dto.UserImpl;
import com.greedy.erp_bomb.member.model.service.MemberService;
import com.greedy.erp_bomb.salary.model.dto.SalaryDTO;
import com.greedy.erp_bomb.salary.model.service.SalaryService;

@Controller
@RequestMapping("/salary")
public class SalaryController {
		
	private SalaryService salaryService;
	private MemberService memberService;
	
	@Autowired
	public SalaryController(SalaryService salaryService) {
		this.salaryService = salaryService;
	}	
	
	@Autowired
	public SalaryController(MemberService memberService) {
		this.memberService = memberService;
	}	
	
	/* 본인 및 모든 급여 목록 조회 */
	@GetMapping("/salary")
	public ModelAndView findAllMySalary(Principal principal, ModelAndView mv) {
		UserImpl user = (UserImpl)((Authentication)principal).getPrincipal();
		
		System.out.println("==========SalaryController===========");
		List<SalaryDTO> salaryList = salaryService.findAllMySalary(user.getName());
		List<SalaryDTO> allSalaryList = salaryService.findAllSalary();

		mv.addObject("salaryList", salaryList);

//		? 로그인 한 사람의 권한 정보 받아오는 법 ? 
//		mv.addObject("user", user);
		mv.addObject("allSalaryList", allSalaryList);
		
		mv.setViewName("/salary/salary");
		
		return mv;
	}

	/* 신규 급여 내역 추가 */
	@PostMapping("regist")
	public ModelAndView registMenu(ModelAndView mv, 
								   @RequestParam String name, 
								   @RequestParam Integer regularPay, 
								   @RequestParam(value="bonus", required=false, defaultValue="0") Integer bonus) {
		
		SalaryDTO salary= new SalaryDTO();
		
		/* 현재 날짜 및 시간 계산 util to sql*/
		Date date = new Date();
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());

		/* member 이름으로 정보 찾아오기 */
		MemberDTO member = (MemberDTO) memberService.loadUserByUsername(name);
		
		System.out.println("controller : " + member);
		
		salary.setDate(sqlDate);
		salary.setBonus(bonus);
		salary.setRegularPay(regularPay);
//		salary.setMember(member);

		System.out.println("controller salary : " + salary);
		
		mv.setViewName("redirect:/salary/salary");
		
		return mv;
	}

}

