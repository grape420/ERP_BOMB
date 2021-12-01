package com.greedy.erp_bomb.salary.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ModelAndView findAllMySalary(Principal principal, ModelAndView mv) {
		UserImpl user = (UserImpl)((Authentication)principal).getPrincipal();
		
		List<SalaryDTO> salaryList = salaryService.findAllMySalary(user.getName());
		List<SalaryDTO> allSalaryList = salaryService.findAllSalary();
		List<MemberDTO> memberList = salaryService.findMemberList();
		
		for (SalaryDTO salaryDTO : allSalaryList) {
			if (salaryDTO.getBonus() == null) {
				salaryDTO.setBonus(0);
			} if (salaryDTO.getRegularPay() == null) {
				salaryDTO.setRegularPay(0);
			}
		}
		
		for (SalaryDTO salaryDTO : salaryList) {
			if (salaryDTO.getBonus() == null) {
				salaryDTO.setBonus(0);
			} if (salaryDTO.getRegularPay() == null) {
				salaryDTO.setRegularPay(0);
			}
		}
		
		mv.addObject("salaryList", salaryList);
		mv.addObject("allSalaryList", allSalaryList);
		mv.addObject("memberList", memberList);
		
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
			System.out.println(curDate);
			
			/* insert를 시도하는 값 */
			Date now = new Date();
			String newDate = sd.format(now);
			System.out.println(newDate);
			
			/* 귀속연월과 이름 중복 */
			if (curDate.equals(newDate) && salaryDTO.getMember().getName().equals(name)){
				// 에러 출력
				flag = false;
				System.out.println("같아요! 들어가면 안됩니다!");
			} 
		}
		
//		if(flag == true) {
			salaryService.registNewSalary(salary);
			System.out.println("controller salary : " + salary);
			mv.setViewName("redirect:/salary/salary");
			
			//flashAttribute
			return mv;
//		} else {
//			mv.setViewName("");
//			return mv;
//		}
	}
	
	/* 급여 검색 - 관리자 O *
	/* 권한 데려오기 */
//	@PostMapping("/search")
//	public String searchInOut(Model model, @RequestParam String keyword, Principal principal) {
//		UserImpl user = (UserImpl)((Authentication)principal).getPrincipal();
//		
//		List<SalaryDTO> salaryList = salaryService.searchSalaryList(keyword, user.getName());
//		
//		model.addAttribute("salaryList", salaryList);
//		
//		return "/salary/salary";
//	}
}

