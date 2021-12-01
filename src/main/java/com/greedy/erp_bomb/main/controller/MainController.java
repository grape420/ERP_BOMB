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
import com.greedy.erp_bomb.vote.model.dto.VoteDTO;

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
		
		Long myApprovalingDocumentCount = mainService.myApprovalingDocumentCount(userName) == null ? 0L : mainService.myApprovalingDocumentCount(userName);
		
		Long myApprovalingCount = mainService.myApprovalingCount(userName) == null ? 0L : mainService.myApprovalingCount(userName);
		
		Long myCarbonCount = mainService.myCarbonCount(userName) == null ? 0L : mainService.myCarbonCount(userName);
		
		List<VoteDTO> voteList = mainService.selectAllVote();
		
		int endVote = 0;
		int voting = 0;
		
		for(VoteDTO vote : voteList) {
			java.sql.Date nowDate = new java.sql.Date(System.currentTimeMillis());
			
			if(vote.getEndDate().before(nowDate)) {
				voting++;
			} else {
				endVote++;
			}
		}
		
		System.out.println("endVote : " + endVote);
		System.out.println("voting : " + voting);
		
		mv.addObject("boardList" ,boardList);
		mv.addObject("myApprovalingDocumentCount" ,myApprovalingDocumentCount);
		mv.addObject("myApprovalingCount" ,myApprovalingCount);
		mv.addObject("myCarbonCount" ,myCarbonCount);
		mv.addObject("voting",voting);
		mv.addObject("endVote",endVote);
		
		mv.setViewName("/main/main");
		return mv;
	}
}

