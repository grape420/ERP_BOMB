package com.greedy.erp_bomb.vote.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.greedy.erp_bomb.common.paging.SelectCriteria;
import com.greedy.erp_bomb.member.model.dto.MemberDTO;
import com.greedy.erp_bomb.vote.model.dto.VoteDTO;
import com.greedy.erp_bomb.vote.model.dto.VoteOptionDTO;
import com.greedy.erp_bomb.vote.model.dto.VoteParticipationDTO;

@Repository
public class VoteDAO {

	@PersistenceContext	
	private EntityManager em;

	public List<VoteDTO> selectALLVote(){
		
		String jpql = "SELECT a FROM VoteDTO as a ORDER BY a.serialNo DESC";
		
		TypedQuery<VoteDTO> query = em.createQuery(jpql, VoteDTO.class);
		
		List<VoteDTO> voteList = query.getResultList();
		
		return voteList;
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

	public VoteDTO selectVoteDetail(int detailnum) {
		
		VoteDTO voteDetail = em.find(VoteDTO.class, detailnum);
		
		int hit = voteDetail.getHit();
		voteDetail.setHit(hit+1);
		
		em.persist(voteDetail);
		
		voteDetail.getVoteOptionList().size();
		voteDetail.getVoteParticipationList().size();
		
		return voteDetail;
	}

	public void insertVvote(VoteParticipationDTO vote, String desc, int serialNo) {
		
		vote.setVote(em.find(VoteDTO.class, serialNo));
		vote.setMember(em.find(MemberDTO.class, vote.getMember().getName()));
		
		vote.getVote().setVoteOptionList(null);
		vote.getVote().setVoteParticipationList(null);
		
		TypedQuery<VoteOptionDTO> query = em.createQuery("SELECT a FROM VoteOptionDTO as a WHERE a.desc = :desc AND a.vote.serialNo = :serialNo", VoteOptionDTO.class);
		
		query.setParameter("desc", desc);
		query.setParameter("serialNo", serialNo);
		
		VoteOptionDTO voteOption = query.getSingleResult();
		
		int num = voteOption.getVoteCount();
		
		voteOption.setVoteCount(num + 1);
		
		em.persist(vote);
		em.persist(voteOption);
	}

	public VoteDTO selectResult(int voteNumber) {
		
		VoteDTO result = em.find(VoteDTO.class, voteNumber);
		
		result.getVoteOptionList().size();
		result.setVoteParticipationList(null);
		
		return result;
	}

	public void insertCandidate(VoteOptionDTO voteOption) {
		voteOption.setMember(em.find(MemberDTO.class, voteOption.getMember().getName()));
		voteOption.setVote(em.find(VoteDTO.class, voteOption.getVote().getSerialNo()));
		
		em.persist(voteOption);
	}
	
	
}
