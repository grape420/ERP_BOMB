package com.greedy.erp_bomb.inventory.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InventoryController {

	@GetMapping("/inventory")
	public String main() {
		return "inventory/inventory";
	}
}
