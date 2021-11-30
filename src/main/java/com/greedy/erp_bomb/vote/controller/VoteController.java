package com.greedy.erp_bomb.vote.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.greedy.erp_bomb.common.paging.Pagenation;
import com.greedy.erp_bomb.common.paging.SelectCriteria;
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
	
	@GetMapping(value = "vote")
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
		
		/* 작성일 */
		java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
		UserImpl user = (UserImpl)((Authentication)principal).getPrincipal();
		
		MemberDTO member = new MemberDTO();
		member.setName(user.getName());
		
		VoteDTO vote = new VoteDTO();
		vote.setMember(member);
		vote.setTitle(title);
		vote.setRegDate(date);
		vote.setEndDate(endDate);
		vote.setContent(content);
		
		/* 신규 투표 작성시 후보가 있나없나 판별 */
		if(!insertMember.isEmpty()) {
			VoteOptionDTO voteOption = new VoteOptionDTO();
			
			voteOption.setVote(vote);
			voteOption.setMember(member);
			voteOption.setDesc(insertMember);
			
			voteService.insertVote(voteOption);
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
		
		/* json 문자열 반환을 위해 DTO안의 List들을 끊어냄 */
		for (VoteOptionDTO vote : voteDetail.getVoteOptionList()) {
			String member = vote.getMember().getName();

			vote.setMember(null);
			vote.setVote(null);
			
			MemberDTO mem = new MemberDTO();
			mem.setName(member);
			
			vote.setMember(mem);
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
		
		String member = voteDetail.getMember().getName();
		MemberDTO mem = new MemberDTO();
		mem.setName(member);
		
		voteDetail.setMember(mem);
		
		return voteDetail;
	}
	
	/* 투표하기 */
	@PostMapping(value = "vvote", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public void vvote(@RequestParam int serialNo, @RequestParam String desc,
			Principal principal) {
		
		UserImpl user = (UserImpl)((Authentication)principal).getPrincipal();
		
		MemberDTO member = new MemberDTO();
		member.setName(user.getName());
		
		VoteParticipationDTO vote = new VoteParticipationDTO();
		
		vote.setMember(member);
		voteService.insertVvote(vote, desc, serialNo);
	}
	
	@GetMapping(value = "resultVote", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public VoteDTO resultvote(@RequestParam int voteNumber) {
		VoteDTO result = voteService.selectResult(voteNumber);
		
		for (VoteOptionDTO vote : result.getVoteOptionList()) {
			vote.setMember(null);
			vote.setVote(null);
		}
		
		result.setMember(null);
		
		return result;
		
	}
	
	@PostMapping(value = "plusCandi", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public void plusCandidate(@RequestParam String candiInsert, @RequestParam int votenum,
			Principal principal) {
		UserImpl user = (UserImpl)((Authentication)principal).getPrincipal();
		
		MemberDTO member = new MemberDTO();
		member.setName(user.getName());
		
		VoteDTO vote = new VoteDTO();
		vote.setSerialNo(votenum);
		
		VoteOptionDTO voteOption = new VoteOptionDTO();
		voteOption.setMember(member);
		voteOption.setVote(vote);
		voteOption.setDesc(candiInsert);
		
		voteService.insertCandidate(voteOption);
		
	}
}
