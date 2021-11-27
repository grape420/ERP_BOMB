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
	public ModelAndView votePage(ModelAndView mv, @RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "") String search) {
		
		int onePost = 10;					// 한 페이지에 노출시킬 게시글의 수
		int onePage = 5;					// 한번에 보여줄 페이지 버튼의 갯수
		int totalVote = 0;
//		search = "ㅁㅁㅁ";
		
		System.out.println("이거옴?" + totalVote);
		
		SelectCriteria selectCriteria = null;
		List<VoteDTO> voteList = null;
		if(search.equals("")) {
			totalVote = voteService.selectAllVote();
			selectCriteria = Pagenation.getSelectCriteria(page, totalVote, onePost, onePage);
			
			System.out.println("이거동작?" + totalVote);
		
		} else {
			totalVote = voteService.selectAllvote(search);
			selectCriteria = Pagenation.getSelectCriteria(page, totalVote, onePage, onePage, null, search);
			
			System.out.println("요게동작?" + totalVote);
		}
		
		System.out.println("selectCriteria임" + selectCriteria);
		voteList = voteService.selectAllVoteList(selectCriteria);
		
		
//		List<VoteDTO> voteList = voteService.selectALLVote();
//		Date date = new Date();
//		List<VoteDTO> endVoteList = new ArrayList<>();
//		List<VoteDTO> regVoteList = new ArrayList<>();
//		
//		for (VoteDTO voteDTO : voteList) {
//			if (voteDTO.getEndDate().before(date)) {
//				endVoteList.add(voteDTO);
//			}
//			if (voteDTO.getEndDate().after(date)) {
//				regVoteList.add(voteDTO);
//			}
//		}
//		
		mv.addObject("selectCriteria", selectCriteria);
		mv.addObject("voteList", voteList);
//		mv.addObject("endVoteList", endVoteList);
//		mv.addObject("regVoteList", regVoteList);
//		
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
		System.out.println(voteDetail.getVoteParticipationList());
		
		String member = voteDetail.getMember().getName();
		MemberDTO mem = new MemberDTO();
		mem.setName(member);
		
		voteDetail.setMember(mem);
		
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
	
	@GetMapping(value = "resultVote", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public VoteDTO resultvote(@RequestParam int voteNumber) {
		VoteDTO result = voteService.selectResult(voteNumber);
		
		System.out.println("여기왔으면 올려");
		
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
