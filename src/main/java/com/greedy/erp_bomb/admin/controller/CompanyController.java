package com.greedy.erp_bomb.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.greedy.erp_bomb.admin.model.service.CompanyService;
import com.greedy.erp_bomb.inventory.model.dto.CompanyDTO;

@Controller
@RequestMapping("/admin")
public class CompanyController {
	
	private CompanyService companyService;
	
	@Autowired
	public CompanyController(CompanyService companyService) {
		this.companyService = companyService;
	}
	
	@GetMapping("/company")
	public ModelAndView findCompanyList(ModelAndView mv) {
		List<CompanyDTO> companyList = companyService.findCompanyList();
		
		mv.addObject("companyList", companyList);
		mv.setViewName("/admin/company");
		
		return mv;
	}
	
	@PostMapping("/newCom")
	public ModelAndView registNewCompany(ModelAndView mv, @ModelAttribute CompanyDTO company) {
		
		company.setDivision("가맹점");
		company.setStatus("Y");
		
		companyService.registNewCompany(company);
		
		mv.setViewName("redirect:/admin/company");
		
		return mv;
	}
	
	@GetMapping(value = "comDetail", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public CompanyDTO comDetail(@RequestParam int detailNum) {
		CompanyDTO com = companyService.comDetail(detailNum);
		
		return com;
	}
	
	@PostMapping("/updateCom")
	public String updateCompany(@ModelAttribute CompanyDTO company) {
		companyService.updateCompany(company);
		
		return "redirect:/admin/company";
	}
	

}
