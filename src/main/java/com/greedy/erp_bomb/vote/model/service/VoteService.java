package com.greedy.erp_bomb.vote.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greedy.erp_bomb.common.paging.SelectCriteria;
import com.greedy.erp_bomb.member.model.dto.MemberDTO;
import com.greedy.erp_bomb.member.model.dto.UserImpl;
import com.greedy.erp_bomb.vote.model.dao.VoteDAO;
import com.greedy.erp_bomb.vote.model.dto.VoteDTO;
import com.greedy.erp_bomb.vote.model.dto.VoteOptionDTO;
import com.greedy.erp_bomb.vote.model.dto.VoteParticipationDTO;

@Service
public class VoteService {

	private VoteDAO voteDAO;
	
	@Autowired
	public VoteService(VoteDAO voteDAO) {
		this.voteDAO = voteDAO;
	}
	
	@Transactional
	public List<VoteDTO> selectALLVote(){
		return voteDAO.selectALLVote();
	}

	
	@Transactional
	public void insertVote(VoteDTO vote) {
		voteDAO.insertVote(vote);
	}
	
	@Transactional
	public void insertVote(VoteOptionDTO voteOption) {
		
		voteDAO.insertVote(voteOption);
		
	}

	@Transactional
	public VoteDTO selectVoteDetail(int detailnum) {
		return voteDAO.selectVoteDetail(detailnum);
	}

	@Transactional
	public void insertVvote(VoteParticipationDTO vote, String votes, int serialNo) {
		voteDAO.insertVvote(vote, votes, serialNo);
	}

	@Transactional
	public VoteDTO selectResult(int voteNumber) {
		return voteDAO.selectResult(voteNumber);
	}

	@Transactional
	public void insertCandidate(VoteOptionDTO voteOption) {
		voteDAO.insertCandidate(voteOption);
	}

}
