package com.greedy.erp_bomb.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.greedy.erp_bomb.admin.model.service.IcecreamService;
import com.greedy.erp_bomb.inventory.model.dto.IceCreamDTO;

@Controller
@RequestMapping("/admin")
public class IcecreamController {
	
	private IcecreamService icecreamService;
	
	@Autowired
	public IcecreamController(IcecreamService icecreamService) {
		this.icecreamService = icecreamService;
	}
	
	@GetMapping("/icecream")
	public ModelAndView findIcecreamList(ModelAndView mv) {
		List<IceCreamDTO> icecreamList = icecreamService.findIcecreamList();
		
		mv.addObject("icecreamList", icecreamList);
		mv.setViewName("/admin/icecream");
		return mv;
	}
	
	@PostMapping("/newIce")
	public ModelAndView registNewIce(ModelAndView mv, @ModelAttribute IceCreamDTO ice) {
		icecreamService.registNewIce(ice);
		
		mv.setViewName("redirect:/admin/icecream");
		
		return mv;
	}
	

}
