package com.greedy.erp_bomb.ea.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.greedy.erp_bomb.board.model.dto.BoardDTO;
import com.greedy.erp_bomb.ea.model.dto.EADTO;
import com.greedy.erp_bomb.vote.model.dto.VoteDTO;
import com.greedy.erp_bomb.vote.model.dto.VoteOptionDTO;

@Repository
public class EaDAO {
	
	@PersistenceContext
	private EntityManager em;

	public EADTO test() {
		return em.find(EADTO.class, 3);
	}

}
