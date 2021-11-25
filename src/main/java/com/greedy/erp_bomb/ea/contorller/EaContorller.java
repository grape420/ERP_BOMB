package com.greedy.erp_bomb.ea.contorller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.greedy.erp_bomb.ea.model.dto.EADTO;
import com.greedy.erp_bomb.ea.model.service.EaService;

@Controller
@RequestMapping("/ea")
public class EaContorller {
	
	private EaService eaService;
	
	@Autowired
	public EaContorller(EaService eaService) {
		this.eaService = eaService;
	}
	
	@GetMapping("/ea")
	public void ea() { }
	
	@GetMapping("/test")
	public String test() {
		
		EADTO ea = eaService.test();
		System.out.println(ea);
		return "main/main";
	}

}