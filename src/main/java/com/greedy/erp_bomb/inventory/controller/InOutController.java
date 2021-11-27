package com.greedy.erp_bomb.inventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greedy.erp_bomb.inventory.model.dto.CompanyDTO;
import com.greedy.erp_bomb.inventory.model.dto.InOutDTO;
import com.greedy.erp_bomb.inventory.model.service.InOutService;

@Controller
@RequestMapping("/inOut")
public class InOutController {
	
	private InOutService inOutService;
	private ObjectMapper objectMapper;
	
	@Autowired
	public InOutController(InOutService inOutService, ObjectMapper objectMapper) {
		this.inOutService = inOutService;
		this.objectMapper = objectMapper;
	}

	@GetMapping("/inOut")
	public ModelAndView findInOutList(ModelAndView mv) {
		List<InOutDTO> inOutList = inOutService.findInOutList();
		
		mv.addObject("inOutList", inOutList);
		mv.setViewName("inOut/inOut");
		
		return mv;
	}
	
	@GetMapping(value = "company", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<CompanyDTO> findCompanyList() {
		return inOutService.findCompanyList();
	}
	
}
