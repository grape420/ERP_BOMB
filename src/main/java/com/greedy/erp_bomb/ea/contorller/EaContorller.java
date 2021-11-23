package com.greedy.erp_bomb.ea.contorller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.greedy.erp_bomb.ea.model.dto.EADTO;
import com.greedy.erp_bomb.ea.model.service.EaService;
import com.greedy.erp_bomb.member.model.dto.UserImpl;

@Controller
@RequestMapping("/ea")
public class EaContorller {
	
	private EaService eaService;
	
	@Autowired
	public EaContorller(EaService eaService) {
		this.eaService = eaService;
	}
	
	@GetMapping("/ea")
	public void ea(Principal principal, Model model) {
		String userName = ((UserImpl)((Authentication)principal).getPrincipal()).getName();
		
		List<EADTO> MyEaList = eaService.findMyEa(userName);
		
		List<EADTO> myEaPathList = eaService.findEaPathList(userName);
		
	}
	
	@GetMapping("/test")
	public String test() {
		EADTO ea = eaService.test();
		return "main/main";
	}

}