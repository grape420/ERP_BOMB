package com.greedy.erp_bomb.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminSpController {
	
	@GetMapping("/sp")
	public ModelAndView findInvenList(ModelAndView mv) {
//		List<InventoryDTO> invenList = inventoryService.findInvenList();
//		
//		mv.addObject("inventoryList", invenList);
		mv.setViewName("admin/sp");
		return mv;
	}
	

}
