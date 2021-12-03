package com.greedy.erp_bomb.admin.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.greedy.erp_bomb.member.model.dto.MemberDTO;
import com.greedy.erp_bomb.salary.model.dto.SalaryDTO;
import com.greedy.erp_bomb.salary.model.service.SalaryService;

@Controller
@RequestMapping("/admin")
public class AdminSalaryController {
		
	private SalaryService salaryService;
	
	@Autowired
	public AdminSalaryController(SalaryService salaryService) {
		this.salaryService = salaryService;
	}	
	
	/* 모든 급여 목록 조회 */
	@GetMapping("/salary")
	public ModelAndView findAllSalary(ModelAndView mv) {
		
		List<SalaryDTO> allSalaryList = salaryService.findAllSalary();
		List<MemberDTO> memberList = salaryService.findMemberList();
		
		for (SalaryDTO salaryDTO : allSalaryList) {
			if (salaryDTO.getBonus() == null) {
				salaryDTO.setBonus(0);
			} if (salaryDTO.getRegularPay() == null) {
				salaryDTO.setRegularPay(0);
			}
		}
		
		mv.addObject("allSalaryList", allSalaryList);
		mv.addObject("memberList", memberList);
		
		mv.setViewName("/admin/salary");
		
		return mv;
	}

	/* 신규 급여 내역 추가 */
	@PostMapping("regist")
	public ModelAndView registMenu(ModelAndView mv, 
								   RedirectAttributes rttr,
								   @RequestParam String name, 
								   @RequestParam Integer regularPay, 
								   @RequestParam(value="bonus", required=false, defaultValue="0") Integer bonus) {
		
		SalaryDTO salary= new SalaryDTO();
		
		/* 현재 날짜 및 시간 계산 util to sql*/
		Date date = new Date();
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());

		/* member 이름으로 정보 찾아오기 */
		MemberDTO member = salaryService.findMemberInfo(name);
		
		salary.setMember(member);
		salary.setDate(sqlDate);
		salary.setBonus(bonus);
		salary.setRegularPay(regularPay);

		boolean flag = true;
		
		/* (이름 + 귀속연월) 기존 값 존재 여부 확인 */
		List<SalaryDTO> allSalaryList = salaryService.findAllSalary();

		for (SalaryDTO salaryDTO : allSalaryList) {
			/* 기존 값 */
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM");
			String curDate = sd.format(salaryDTO.getDate());
			
			/* insert를 시도하는 값 */
			Date now = new Date();
			String newDate = sd.format(now);
			
			/* 귀속연월과 이름 중복 */
			if (curDate.equals(newDate) && salaryDTO.getMember().getName().equals(name)){
				// 에러 출력
				flag = false;
				rttr.addFlashAttribute("flashName", name);
				rttr.addFlashAttribute("flashDate", curDate);
			}
		}
		
		if(flag == true) {
			salaryService.registNewSalary(salary);
			System.out.println("controller salary : " + salary);
		}
		mv.setViewName("redirect:/admin/salary");
		return mv;
	}
}

