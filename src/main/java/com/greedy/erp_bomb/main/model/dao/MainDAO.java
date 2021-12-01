package com.greedy.erp_bomb.main.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.greedy.erp_bomb.board.model.dto.BoardDTO;

@Repository
public class MainDAO {
	
	@PersistenceContext
	private EntityManager em;

	public List<BoardDTO> selectMainPageBoardList() {
		String jpql = "SELECT a FROM BoardDTO as a ORDER BY a.no DESC";
		
		return em.createQuery(jpql, BoardDTO.class).setMaxResults(5).getResultList();
	}

	public Integer myApprovalingDocumentCount(String userName) {
		String jqpl = "SELECT COUNT(a) FROM EADTO as a WHERE a.eaStatus = 1";
		
		return 0;
	}

}
