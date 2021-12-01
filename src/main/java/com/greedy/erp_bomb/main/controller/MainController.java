package com.greedy.erp_bomb.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping(value={"/", "/main"})
	public String main() {
		return "main/main";
	}
	
	@GetMapping("/main/main")
	public void mainMapping() {}
	
}
