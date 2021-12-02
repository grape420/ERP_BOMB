package com.greedy.erp_bomb.tna.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.greedy.erp_bomb.member.model.dto.MemberDTO;
import com.greedy.erp_bomb.tna.model.dto.TNADTO;
import com.greedy.erp_bomb.tna.model.dto.TNAPk;

@Repository
public class TNADAO {

	@PersistenceContext
	private EntityManager em;

	public List<TNADTO> tnaDateSearch(String find) {
		String jpql = null;
		List<TNADTO> data = null;
		
		if(find.equals("0")) {
			jpql = "SELECT a FROM TNADTO as a";
			data = em.createQuery(jpql, TNADTO.class).getResultList();
		} else {
			jpql = "SELECT a FROM TNADTO as a WHERE a.date = :find";
			data = em.createQuery(jpql, TNADTO.class).setParameter("find", find).getResultList();
		}
		
		return data;
	}

	public List<TNADTO> selectDetail(String name) {
		String jpql = "SELECT a FROM TNADTO as a WHERE a.member.name = :name";
		
		List<TNADTO> data = em.createQuery(jpql, TNADTO.class).setParameter("name", name).getResultList();
		
		return data;
	}

	public void regiTna(TNADTO tna) {
		String jpql = "SELECT a FROM TNADTO as a WHERE a.member.name = :name AND a.date = :date";
		
		TNADTO regiTna = em.createQuery(jpql, TNADTO.class).setParameter("name", tna.getMember().getName()).setParameter("date", tna.getDate()).getSingleResult();
		
		regiTna.setCode(tna.getCode());
		
	}

	public void deletWork(TNADTO tna) {
		
		TNAPk tnaPK = new TNAPk();
		tnaPK.setDate(tna.getDate());
		tnaPK.setMember(tna.getMember().getName());
		
		TNADTO deletMember = em.find(TNADTO.class, tnaPK);
		
		em.remove(deletMember);
		
	}

	public void newWork(TNADTO tna) {
		
		MemberDTO member = em.find(MemberDTO.class, tna.getMember().getName());
		
		tna.setMember(member);
		
		em.persist(tna);
	}
}
