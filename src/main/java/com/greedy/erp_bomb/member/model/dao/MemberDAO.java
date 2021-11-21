package com.greedy.erp_bomb.member.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.greedy.erp_bomb.member.model.dto.MemberDTO;

@Repository
public class MemberDAO {
	
	@PersistenceContext
	private EntityManager em;

	public MemberDTO findMemberById(String name) {
		return em.find(MemberDTO.class, name);
	}
	
}