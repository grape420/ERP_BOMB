package com.greedy.erp_bomb.sp.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.greedy.erp_bomb.member.model.dto.MemberDTO;
import com.greedy.erp_bomb.member.model.dto.UserImpl;
import com.greedy.erp_bomb.sp.model.dto.SPDTO;
import com.greedy.erp_bomb.sp.model.service.SpService;

@Controller
@RequestMapping("/sp")
public class SpController {
	
	private SpService spService;
	
	@Autowired
	public SpController(SpService spService) {
		this.spService = spService;
	}
	
	/* 퇴직금 리스트 */
	@GetMapping("/sp")
	public ModelAndView findSpList(ModelAndView mv, Principal principal) {
		UserImpl user = (UserImpl)((Authentication)principal).getPrincipal();
		
		List<MemberDTO> member = spService.findEntryMember();
		
		List<SPDTO> spList = spService.findSpList();
		
		mv.addObject("entYn", member);
		mv.addObject("user", user);
		mv.addObject("spList", spList);
		
		mv.setViewName("sp/sp");
		
		/* dd*/
		return mv;
	}
	
	/* 퇴직금 내역 추가 */
	@PostMapping("regSp")
	public ModelAndView registSpList(ModelAndView mv, @RequestParam String name, 
									@RequestParam Integer serverancePay, @RequestParam Integer empYear) {
		
		MemberDTO member = spService.findMemberInfo(name);
		
		SPDTO sp = new SPDTO();
		sp.setServerancePay(serverancePay);
		sp.setEmpYear(empYear);
		sp.setMember(member);
		
		spService.newRegistSp(sp);
		
		mv.setViewName("redirect:/sp/sp");
		
		return mv;
	}
	
	@PostMapping("updateSp")
	public ModelAndView UpdateSpList(ModelAndView mv, @RequestParam String name, @RequestParam Integer serverancePay, @RequestParam Integer empYear) {
		
		MemberDTO member = new MemberDTO();
		member = spService.findMemberInfo(name);
		
		SPDTO sp2 = new SPDTO();
		sp2.setEmpYear(empYear);
		sp2.setMember(member);
		sp2.setServerancePay(serverancePay);
		
		spService.updateSp(sp2);
		
		System.out.println("에러 확인??");
		
		mv.setViewName("redirect:/sp/sp");
		
		return mv;
	}
	
}
