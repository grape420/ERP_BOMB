package com.greedy.erp_bomb.member.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.greedy.erp_bomb.member.model.dto.MemberDTO;
import com.greedy.erp_bomb.vote.model.dto.VoteDTO;
import com.greedy.erp_bomb.vote.model.dto.VoteOptionDTO;

@Repository
public class MemberDAO {
	
	@PersistenceContext
	private EntityManager em;

	public MemberDTO findMemberById(String name) {
		MemberDTO member = em.find(MemberDTO.class, name);
		
		VoteDTO vote = em.find(VoteDTO.class, 2);
		
		String jpql = "SELECT a FROM VoteOptionDTO as a WHERE a.vote.serialNo = 2";
		
		vote.setVoteOptionList(em.createQuery(jpql, VoteOptionDTO.class).getResultList());
		
		for(VoteOptionDTO voteop : vote.getVoteOptionList()) {
			System.out.println(voteop);
		}
		return member;
	}
	
}