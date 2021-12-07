package com.greedy.erp_bomb.tna.contorller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.greedy.erp_bomb.inventory.model.dto.CompanyDTO;
import com.greedy.erp_bomb.member.model.dto.DeptDTO;
import com.greedy.erp_bomb.member.model.dto.MemberDTO;
import com.greedy.erp_bomb.member.model.dto.RankDTO;
import com.greedy.erp_bomb.tna.model.dto.TNADTO;
import com.greedy.erp_bomb.tna.model.service.TNAService;

@Controller
@RequestMapping("/admin")
public class TNAContorller {
	
	private TNAService tnaService;
	
	@Autowired
	public TNAContorller(TNAService tnaService) {
		this.tnaService = tnaService;
	}
	
	@GetMapping("/tna")
	public String regTna() {
		return "/admin/regTna";
	}
	
	@GetMapping("/tnaDetail")
	public void tnaDetail() {}
	
	@GetMapping(value = "/tnaList", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<TNADTO> dateSearch(@RequestParam String find){
		
		List<TNADTO> data = tnaService.tnaDateSearch(find);
		
		for (TNADTO tna : data) {
			String name = tna.getMember().getName();
			int number = tna.getMember().getEmpNo();
			String dept = tna.getMember().getDept().getName();
			String company = tna.getMember().getCompany().getName();
			String rank = tna.getMember().getRank().getName();
			
			if(tna.getCode() == 1) {
				tna.setStatus("출근");
			} else if(tna.getCode() == 2) {
				tna.setStatus("지각");
			} else if(tna.getCode() == 3) {
				tna.setStatus("조퇴");
			} else if(tna.getCode() == 4) {
				tna.setStatus("결근");
			}
			
			DeptDTO dep = new DeptDTO();
			dep.setName(dept);
			
			RankDTO ran = new RankDTO();
			ran.setName(rank);
			
			CompanyDTO comp = new CompanyDTO();
			comp.setName(company);

			MemberDTO member = new MemberDTO();
			member.setName(name);
			member.setEmpNo(number);
			member.setRank(ran);
			member.setCompany(comp);
			member.setDept(dep);
			
			tna.setMember(null);
			tna.setMember(member);
			
		}
		
		return data;
	}
	
	@GetMapping("/detail")
	public ModelAndView detailMember(ModelAndView mv, @RequestParam String name) {
		
		List<TNADTO> member = tnaService.selectDetail(name);
		MemberDTO member1 = null;
		for (TNADTO tna : member) {
			
			if(tna.getCode() == 1) {
				tna.setStatus("출근");
			} else if(tna.getCode() == 2) {
				tna.setStatus("지각");
			} else if(tna.getCode() == 3) {
				tna.setStatus("조퇴");
			} else if(tna.getCode() == 4) {
				tna.setStatus("결근");
			}
			
			member1 = tna.getMember();
		}
		
		System.out.println("member1은 ? " + member1);
		
		
		mv.addObject("member", member);
		mv.addObject("mem", member1);
		
		mv.setViewName("/admin/tnaDetail");
		
		return mv;
	}
	
	@GetMapping("/regiTna")
	public ModelAndView regiTna(ModelAndView mv, @RequestParam String name, @RequestParam String date,
			@RequestParam int selectStat) {
		MemberDTO member = new MemberDTO();
		member.setName(name);
		
		TNADTO tna = new TNADTO();
		tna.setCode(selectStat);
		tna.setDate(date);
		tna.setMember(member);
		
		tnaService.regiTna(tna);
		
		mv.addObject("name", name);
		
		mv.setViewName("redirect:/admin/detail");
		
		return mv;
	}
	
	@GetMapping("/deleteWork")
	public ModelAndView deletWork(ModelAndView mv, @RequestParam String name, @RequestParam String date) {
		
		MemberDTO member = new MemberDTO();
		member.setName(name);
		
		TNADTO tna = new TNADTO();
		tna.setDate(date);
		tna.setMember(member);
		
		tnaService.deletWork(tna);
		
		mv.addObject("name", name);
		
		mv.setViewName("redirect:/admin/detail");
		
		return mv;
	}
	
	@GetMapping("/newWork")
	public ModelAndView newWork(ModelAndView mv, @RequestParam String name, @RequestParam String date,
			@RequestParam int selectStat) {
		
		MemberDTO member = new MemberDTO();
		member.setName(name);
		
		TNADTO tna = new TNADTO();
		tna.setCode(selectStat);
		tna.setDate(date);
		tna.setMember(member);
		
		System.out.println(tna);
		tnaService.newWork(tna);
		
		mv.addObject("name", name);
		
		mv.setViewName("redirect:/admin/detail");
		
		return mv;
	}
}
