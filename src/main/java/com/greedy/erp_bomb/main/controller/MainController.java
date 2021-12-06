package com.greedy.erp_bomb.main.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.greedy.erp_bomb.board.model.dto.BoardDTO;
import com.greedy.erp_bomb.main.model.service.MainService;
import com.greedy.erp_bomb.main.model.vo.FullCalendarVO;
import com.greedy.erp_bomb.member.model.dto.MemberDTO;
import com.greedy.erp_bomb.member.model.dto.UserImpl;
import com.greedy.erp_bomb.tna.model.dto.TNADTO;
import com.greedy.erp_bomb.tna.model.dto.TNAPk;
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
			
			if(vote.getEndDate().after(nowDate)) {
				voting++;
			} else {
				endVote++;
			}
		}
		
		TNADTO tna = mainService.findTodayWork(new TNAPk(new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()), userName));
		System.out.println("tna : " + tna);
		if(tna != null) {
			mv.addObject("todayWorkFlag", false);
		} else {
			mv.addObject("todayWorkFlag", true);
		}
		
		mv.addObject("boardList" ,boardList);
		mv.addObject("myApprovalingDocumentCount" ,myApprovalingDocumentCount);
		mv.addObject("myApprovalingCount" ,myApprovalingCount);
		mv.addObject("myCarbonCount" ,myCarbonCount);
		mv.addObject("voting",voting);
		mv.addObject("endVote",endVote);
		
		mv.setViewName("/main/main");
		return mv;
	}
	
	@GetMapping("/main/entWork")
	public ModelAndView entWork(ModelAndView mv,  @AuthenticationPrincipal UserImpl user) {
		TNADTO tna = new TNADTO();
		MemberDTO member = new MemberDTO();
		member.setName(user.getName());
		
		tna.setDate(new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
		tna.setMember(member);
		tna.setCode(1);
		
		mainService.regEntWork(tna);
		
		mv.setViewName("redirect:/main/main");
		
		return mv;
	}
	
	@GetMapping("/main/fullCalendar")
	@ResponseBody
	public List<FullCalendarVO> insertDataInFullCalendar(@AuthenticationPrincipal UserImpl user) {
		List<TNADTO> myTnaList = mainService.findMyTnaList(user.getName());
		
		List<FullCalendarVO> ajaxMyTnaList = new ArrayList<>();
		
		for(TNADTO tna : myTnaList) {
			ajaxMyTnaList.add(new FullCalendarVO("출근", tna.getDate(), tna.getDate(), "false"));
		}
		
		return ajaxMyTnaList;
	}
}
































