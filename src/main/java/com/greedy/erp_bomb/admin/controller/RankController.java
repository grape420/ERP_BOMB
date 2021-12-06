package com.greedy.erp_bomb.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.greedy.erp_bomb.admin.model.service.RankService;
import com.greedy.erp_bomb.member.model.dto.RankDTO;

@Controller
@RequestMapping("/admin")
public class RankController {
	
	private RankService rankService;
	
	@Autowired
	public RankController(RankService rankService) {
		this.rankService = rankService;
	}
	
	@GetMapping("/rank")
	public ModelAndView findRankList(ModelAndView mv) {
		List<RankDTO> rankList = rankService.findRankList();
		
		mv.addObject("rankList", rankList);
		mv.setViewName("admin/rank");
		return mv;
	}
	
	@PostMapping("/newRank")
	public ModelAndView registNewRank(ModelAndView mv, @ModelAttribute RankDTO rank) {
		rankService.registNewRank(rank);
		
		mv.setViewName("redirect:/admin/rank");
		
		return mv;
	}
	

}
