package com.greedy.erp_bomb.vote.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.greedy.erp_bomb.member.model.dto.MemberDTO;
import com.greedy.erp_bomb.member.model.dto.UserImpl;
import com.greedy.erp_bomb.vote.model.dto.VoteDTO;
import com.greedy.erp_bomb.vote.model.dto.VoteOptionDTO;

@Repository
public class VoteDAO {

	@PersistenceContext	
	private EntityManager em;

	public List<VoteDTO> selectALLVote(){
		
		String jpql = "SELECT a FROM VoteDTO as a ORDER BY a.serialNo DESC";
		
		TypedQuery<VoteDTO> query = em.createQuery(jpql, VoteDTO.class);
		
		List<VoteDTO> voteList = query.getResultList();
		
		System.out.println(voteList);
		return voteList;
	}

	public MemberDTO selectMember(UserImpl user) {
		return em.find(MemberDTO.class, user.getName());
	}

	public void insertVote(VoteDTO vote) {
		em.persist(vote);
		
	}

	public void insertVote(VoteOptionDTO voteOption) {
		voteOption.getVote().getVoteOptionList().add(voteOption);
		voteOption.setMember(em.find(MemberDTO.class, voteOption.getVote().getMember().getName()));
		
		em.persist(voteOption);
		System.out.println(voteOption);
	}
}
