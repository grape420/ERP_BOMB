package com.greedy.erp_bomb.sp.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.greedy.erp_bomb.sp.model.dto.SPDTO;

@Repository
public class SpDAO {

	@PersistenceContext
	private EntityManager em;

	public List<SPDTO> findSpList() {
		String jpql = "SELECT m FROM SPDTO as m ORDER BY m.spNo" ;
		
		TypedQuery<SPDTO> query = em.createQuery(jpql, SPDTO.class);
		
		List<SPDTO> spList = query.getResultList();
		
		System.out.println(spList);
		
		return spList;
	}

	public void newRegistSp(SPDTO sp ) {
		
		em.persist(sp);
	}

	public List<SPDTO> findDetailSp(String userName) {
		String jpql = "SELECT m FROM SPDTO as m WHERE m.member.name = :name";
		
		List<SPDTO> detailSp = em.createQuery(jpql, SPDTO.class).setParameter("name", userName).getResultList();
		
		
		for(SPDTO sp : detailSp) {
			System.out.println(sp);
		}
		
		return detailSp;
	}
	

//	public List<MemberDTO> findMemberList() {
//		String jpql = "SELECT m FROM MemberDTO as m";
//		
//		List<MemberDTO> memberList = em.createQuery(jpql, MemberDTO.class).getResultList();
//		
//		return memberList;
//	}

	
}
