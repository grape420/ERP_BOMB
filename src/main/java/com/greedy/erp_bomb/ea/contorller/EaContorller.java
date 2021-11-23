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
		
		List<EADTO> myEaList = eaService.findMyEa(userName);
		List<EADTO> myEaPathList = eaService.findMyEaPathList(userName);
		List<EADTO> myEaCarbonList = eaService.findMyEaCarbonList(userName);
		
		model.addAttribute("myEaList", myEaList);
		model.addAttribute("myEaPathList", myEaPathList);
		model.addAttribute("myEaCarbonList", myEaCarbonList);
	}
	
}