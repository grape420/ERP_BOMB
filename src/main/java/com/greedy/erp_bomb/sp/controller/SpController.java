package com.greedy.erp_bomb.sp.controller;

import java.security.Principal;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greedy.erp_bomb.member.model.dto.MemberDTO;
import com.greedy.erp_bomb.member.model.dto.UserImpl;
import com.greedy.erp_bomb.sp.model.dto.SPDTO;
import com.greedy.erp_bomb.sp.model.service.SpService;

@Controller
@RequestMapping("/sp")
public class SpController {
	
	private SpService spService;
	private ObjectMapper objectMapper;
	
	@Autowired
	public SpController(SpService spService, ObjectMapper objectMapper) {
		this.spService = spService;
		this.objectMapper = objectMapper;
	}
	
	/* 퇴직금 리스트 */
	@GetMapping("/sp")
	public ModelAndView findSpList(ModelAndView mv, Principal principal) {
		UserImpl user = (UserImpl)((Authentication)principal).getPrincipal();
		
		List<SPDTO> member = spService.findEntryMember();
		
		List<SPDTO> detailSp = spService.findDetailSp(user.getName());
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
	
	@GetMapping("updateSp")
	@ResponseBody
	public void UpdateSpList(@RequestParam String name, 
									@RequestParam Integer serverancePay, @RequestParam Integer empYear) {
		
		MemberDTO member = new MemberDTO();
		member = spService.findMemberInfo(name);
		
		SPDTO sp2 = new SPDTO();
		sp2.setEmpYear(empYear);
		sp2.setMember(member);
		sp2.setServerancePay(serverancePay);
		
		spService.updateSp(sp2);
		
		System.out.println("에러 확인??");
		
	}
	
//	@GetMapping(value = "member", produces = "application/json; charset=UTF-8")
//	@ResponseBody
//	public List<MemberDTO> findMemberList() {
//		return spService.findMemberList();
//	}
}
