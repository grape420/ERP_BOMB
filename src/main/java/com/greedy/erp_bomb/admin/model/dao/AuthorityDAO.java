package com.greedy.erp_bomb.admin.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.greedy.erp_bomb.member.model.dto.MemberDTO;
import com.greedy.erp_bomb.member.model.dto.MemberRoleDTO;

@Repository
public class AuthorityDAO {

	@PersistenceContext
	private EntityManager em;

	public List<MemberDTO> findAuthorityList() {
		String jpql = "SELECT a FROM MemberDTO a ORDER BY a.name";
		
		List<MemberDTO> memberList = em.createQuery(jpql, MemberDTO.class).getResultList();
		
		return memberList;
	}

	public MemberRoleDTO findAuthDetail(String detailName) {
		System.out.println("이름 뭐야 : " + detailName);
		String jpql = "SELECT a FROM MemberRoleDTO a WHERE a.member.name = :detailName ORDER BY a.member.name";
		
		MemberRoleDTO memberRole = em.createQuery(jpql, MemberRoleDTO.class)
				                     .setParameter("detailName", detailName)
				                     .getSingleResult();
		return memberRole;
	}

}
