package com.greedy.erp_bomb.admin.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.greedy.erp_bomb.inventory.model.dto.InventoryDTO;

@Controller
@RequestMapping("/admin")
public class AuthorityController {
	
//	@GetMapping("/authority")
//	public String authority() {
//		return "admin/authority";
//	}
	
	@GetMapping("/authority")
	public ModelAndView findInvenList(ModelAndView mv) {
//		List<InventoryDTO> invenList = inventoryService.findInvenList();
//		
//		mv.addObject("inventoryList", invenList);
		mv.setViewName("admin/authority");
		return mv;
	}
	

}
