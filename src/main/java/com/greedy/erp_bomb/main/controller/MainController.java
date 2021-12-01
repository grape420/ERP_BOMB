package com.greedy.erp_bomb.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.greedy.erp_bomb.board.model.dto.BoardDTO;
import com.greedy.erp_bomb.main.model.service.MainService;
import com.greedy.erp_bomb.member.model.dto.UserImpl;

@Controller
public class MainController {
	
	private MainService mainService;
	
	@Autowired
	public MainController(MainService mainService) {
		this.mainService = mainService;
	}
	
	@GetMapping(value={"/", "/main", "/main/main"})
	public ModelAndView main(ModelAndView mv, @AuthenticationPrincipal UserImpl user) {
		
		List<BoardDTO> boardList = mainService.selectMainPageBoardList();
		
		String userName = user.getName();
		
		Integer myApprovalingDocumentCount = mainService.myApprovalingDocumentCount(userName);
		
		mv.addObject("boardList" ,boardList);
		
		mv.setViewName("/main/main");
		return mv;
	}
}
