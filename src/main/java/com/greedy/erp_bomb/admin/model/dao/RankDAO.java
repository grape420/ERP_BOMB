package com.greedy.erp_bomb.admin.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.greedy.erp_bomb.member.model.dto.RankDTO;

@Repository
public class RankDAO {
	
	@PersistenceContext
	private EntityManager em;

	public List<RankDTO> findRankList() {
		String jpql = "SELECT a FROM RankDTO a";
		
		List<RankDTO> rankList = em.createQuery(jpql, RankDTO.class).getResultList();
		
		return rankList;
	}

	public void registNewRank(RankDTO rank) {
		em.persist(rank);
	}
	

}
