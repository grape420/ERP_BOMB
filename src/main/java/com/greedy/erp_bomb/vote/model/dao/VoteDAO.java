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
		
		System.out.println(voteList);
		return voteList;
	}

	public List<VoteDTO> selectAllVoteList(SelectCriteria selectCriteria) {
		String jpql = null;
		List<VoteDTO> vote = null;
		int first = 1;
		
		if(selectCriteria.getPageNo() == 1) {
			first = 1;
		} else {
			first = selectCriteria.getPageNo() * selectCriteria.getLimit() - selectCriteria.getLimit() + 1;
		}
		if(selectCriteria.getSearchValue() == null) {
			jpql = "SELECT a FROM VoteDTO as a ORDER BY a.serialNo DESC";
			
			vote = em.createQuery(jpql, VoteDTO.class)
					.setFirstResult(first - 1)
					.setMaxResults(selectCriteria.getLimit())
					.getResultList();

		} else {
			jpql = "SELECT a FROM VoteDTO as a WHERE a.title LIKE CONCAT('%',:searchValue,'%') ORDER BY a.serialNo DESC ";

			vote = em.createQuery(jpql, VoteDTO.class)
					.setParameter("searchValue", selectCriteria.getSearchValue())
					.setFirstResult(selectCriteria.getPageNo() - 1)
					.setMaxResults(selectCriteria.getLimit())
					.getResultList();
		}
		
											
		
		return vote;
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

	public void insertVvote(VoteParticipationDTO vote, String votes, int serialNo) {
		
		vote.setVote(em.find(VoteDTO.class, serialNo));
		vote.setMember(em.find(MemberDTO.class, vote.getMember().getName()));
		
		System.out.println(vote.getMember().getName());
		System.out.println(vote.getVote());
		
		vote.getVote().setVoteOptionList(null);
		vote.getVote().setVoteParticipationList(null);
		
		TypedQuery<VoteOptionDTO> query = em.createQuery("SELECT a FROM VoteOptionDTO as a WHERE a.desc = :desc", VoteOptionDTO.class);
		
		query.setParameter("desc", votes);
		
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

	public int selectAllVote() {
		
		return ((Number) em.createQuery("select count(*) from VoteDTO")
				.getSingleResult()).intValue();
	}

	public int selectAllVote(String search) {
		return ((Number) em.createQuery("select count(*) from VoteDTO as a WHERE a.title LIKE CONCAT('%',:search,'%')")
				.setParameter("search", search)
				.getSingleResult()).intValue();
	}

	
	
}
