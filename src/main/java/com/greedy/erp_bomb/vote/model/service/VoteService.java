package com.greedy.erp_bomb.vote.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greedy.erp_bomb.member.model.dto.MemberDTO;
import com.greedy.erp_bomb.member.model.dto.UserImpl;
import com.greedy.erp_bomb.vote.model.dao.VoteDAO;
import com.greedy.erp_bomb.vote.model.dto.VoteDTO;
import com.greedy.erp_bomb.vote.model.dto.VoteOptionDTO;

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
	public MemberDTO selectMember(UserImpl user) {
		return voteDAO.selectMember(user);
	}
	
	@Transactional
	public void insertVote(VoteDTO vote) {
		voteDAO.insertVote(vote);
	}
	
	@Transactional
	public void insertVote(VoteOptionDTO voteOption) {
		
		voteDAO.insertVote(voteOption);
		
	}
}
