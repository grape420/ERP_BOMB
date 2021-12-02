package com.greedy.erp_bomb.tna.contorller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.greedy.erp_bomb.tna.model.service.TNAService;

@Controller
@RequestMapping("/tna")
public class TNAContorller {
	
	private TNAService tnaService;
	
	@Autowired
	public TNAContorller(TNAService tnaService) {
		this.tnaService = tnaService;
	}
	
}
