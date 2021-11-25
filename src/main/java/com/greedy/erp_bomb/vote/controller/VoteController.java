package com.greedy.erp_bomb.vote.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.greedy.erp_bomb.member.model.dto.MemberDTO;
import com.greedy.erp_bomb.member.model.dto.UserImpl;
import com.greedy.erp_bomb.vote.model.dto.VoteDTO;
import com.greedy.erp_bomb.vote.model.dto.VoteOptionDTO;
import com.greedy.erp_bomb.vote.model.dto.VoteParticipationDTO;
import com.greedy.erp_bomb.vote.model.service.VoteService;

@Controller
@RequestMapping("/vote/*")
public class VoteController {

	private VoteService voteService;
	
	public VoteController(VoteService voteService) {
		this.voteService = voteService;
	}
	
	@GetMapping(value = "vote", produces = "application/json; charset=UTF-8")
	public ModelAndView votePage(ModelAndView mv) {
		
		List<VoteDTO> voteList = voteService.selectALLVote();
		Date date = new Date();
		List<VoteDTO> endVoteList = new ArrayList<>();
		List<VoteDTO> regVoteList = new ArrayList<>();
		
		for (VoteDTO voteDTO : voteList) {
			if (voteDTO.getEndDate().before(date)) {
				endVoteList.add(voteDTO);
			}
			if (voteDTO.getEndDate().after(date)) {
				regVoteList.add(voteDTO);
			}
		}
		
		mv.addObject("voteList", voteList);
		mv.addObject("endVoteList", endVoteList);
		mv.addObject("regVoteList", regVoteList);
		
		mv.setViewName("/vote/vote");
		
		return mv;
	}
	
	@PostMapping("insertVote")
	public ModelAndView insertVote(ModelAndView mv, @RequestParam String title, @RequestParam String insertMember,
			@RequestParam java.sql.Date endDate, @RequestParam String content
			, Principal principal) {
		
		java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
		UserImpl user = (UserImpl)((Authentication)principal).getPrincipal();
		
		System.out.println(user.getName());
		
		MemberDTO member = new MemberDTO();
		member.setName(user.getName());
		
		VoteDTO vote = new VoteDTO();
		vote.setMember(member);
		vote.setTitle(title);
		vote.setRegDate(date);
		vote.setEndDate(endDate);
		vote.setContent(content);
		
		System.out.println(insertMember);
		
		if(!insertMember.isEmpty()) {
			VoteOptionDTO voteOption = new VoteOptionDTO();
			
			voteOption.setVote(vote);
			voteOption.setMember(member);
			voteOption.setDesc(insertMember);
			
			voteService.insertVote(voteOption);
			
			System.out.println("보트" + vote);
		} else {
			voteService.insertVote(vote);
		}
		
		mv.setViewName("redirect:/vote/vote");
		
		return mv;
	}
	
	@GetMapping(value = "detail", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public VoteDTO voteDetail(@RequestParam int detailnum) {
		
		VoteDTO voteDetail = voteService.selectVoteDetail(detailnum);
		
		for (VoteOptionDTO vote : voteDetail.getVoteOptionList()) {
			vote.setMember(null);
			vote.setVote(null);
		}
		
		for (VoteParticipationDTO votePa : voteDetail.getVoteParticipationList()) {
			String member = votePa.getMember().getName();
			int voteNum = votePa.getVote().getSerialNo();
			
			votePa.setMember(null);
			votePa.setVote(null);
			
			MemberDTO mem = new MemberDTO();
			mem.setName(member);
			
			VoteDTO vote = new VoteDTO();
			vote.setSerialNo(voteNum);
			
			votePa.setMember(mem);
			votePa.setVote(vote);
		}
		System.out.println(voteDetail.getVoteParticipationList());
		voteDetail.setMember(null);
		
		return voteDetail;
	}
	
	@PostMapping("/vvote")
	public ModelAndView vvote(ModelAndView mv, @RequestParam int serialNo, @RequestParam String votes,
			Principal principal) {
		
		System.out.println("넘버다" + serialNo);
		
		UserImpl user = (UserImpl)((Authentication)principal).getPrincipal();
		
		System.out.println(user.getName());
		
		MemberDTO member = new MemberDTO();
		member.setName(user.getName());
		
		VoteParticipationDTO vote = new VoteParticipationDTO();
		
		vote.setMember(member);
		
		voteService.insertVvote(vote, votes, serialNo);
		
		mv.setViewName("redirect:/vote/vote");
		
		return mv;
	}
	
}
