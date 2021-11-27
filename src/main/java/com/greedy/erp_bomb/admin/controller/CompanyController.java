package com.greedy.erp_bomb.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.greedy.erp_bomb.admin.model.service.CompanyService;
import com.greedy.erp_bomb.inventory.model.dto.CompanyDTO;
import com.greedy.erp_bomb.inventory.model.dto.IceCreamDTO;
import com.greedy.erp_bomb.inventory.model.dto.InventoryDTO;
import com.greedy.erp_bomb.inventory.model.dto.InventoryPk;

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
		
		List<IceCreamDTO> iceList = companyService.findIceList();
		
		InventoryDTO inven = new InventoryDTO();
		inven.setCompany(company);
		for (IceCreamDTO ice : iceList) {
			inven.setIceCream(ice);
		}
		companyService.registNewInven(inven, iceList);

		mv.setViewName("redirect:/admin/company");
		
		return mv;
	}
	

}
