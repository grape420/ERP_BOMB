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
		EADTO ea = em.find(EADTO.class, 3);
		
		System.out.println("EADTO 찾아 왔는지 테스트");
		
		System.out.println("참조 리스트 사이즈 : " + ea.getEaCarbonList().size());
		System.out.println("첨언 리스트 사이즈 : " + ea.getAddendumList().size());
		System.out.println("결재 라인 리스트 사이즈 : " + ea.getEaApprovalPathList().size());
		
		return ea;
	}

}
