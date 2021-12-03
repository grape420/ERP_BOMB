package com.greedy.erp_bomb.main.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.greedy.erp_bomb.board.model.dto.BoardDTO;
import com.greedy.erp_bomb.member.model.dto.MemberDTO;
import com.greedy.erp_bomb.tna.model.dto.TNADTO;
import com.greedy.erp_bomb.tna.model.dto.TNAPk;
import com.greedy.erp_bomb.vote.model.dto.VoteDTO;

@Repository
public class MainDAO {
	
	@PersistenceContext
	private EntityManager em;

	public List<BoardDTO> selectMainPageBoardList() {
		String jpql = "SELECT a FROM BoardDTO as a ORDER BY a.no DESC";
		return em.createQuery(jpql, BoardDTO.class).setMaxResults(5).getResultList();
	}

	public Long myApprovalingDocumentCount(String userName) {
		String jpql = "SELECT COUNT(a) FROM EADTO as a WHERE a.eaStatus = 1 AND a.member.name = :userName";
		return em.createQuery(jpql, Long.class).setParameter("userName", userName).getSingleResult();
	}

	public Long myApprovalingCount(String userName) {
		String jpql = "SELECT COUNT(a) FROM EAPathDTO as a WHERE a.status = 4 AND a.member.name = :userName";
		return em.createQuery(jpql, Long.class).setParameter("userName", userName).getSingleResult();
	}

	public Long myCarbonCount(String userName) {
		String jpql = "SELECT COUNT(a) FROM EACarbonDTO as a WHERE a.ea.eaStatus = 4 AND a.member.name = :userName";
		return em.createQuery(jpql, Long.class).setParameter("userName", userName).getSingleResult();
	}

	public List<VoteDTO> selectAllVote() {
		String jpql = "SELECT a FROM VoteDTO as a";
		return em.createQuery(jpql, VoteDTO.class).getResultList();
	}

	public void regEntWork(TNADTO tna) {
		tna.setMember(em.find(MemberDTO.class, tna.getMember().getName()));
		em.persist(tna);
	}

	public TNADTO findTodayWork(TNAPk tnaPk) {
		return em.find(TNADTO.class, tnaPk);
	}

	public List<TNADTO> findMyTnaList(String name) {
		String jpql = "SELECT a FROM TNADTO as a WHERE a.member.name = :userName";
		return em.createQuery(jpql, TNADTO.class).setParameter("userName", name).getResultList();
	}

}
