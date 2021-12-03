package com.greedy.erp_bomb.admin.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.greedy.erp_bomb.member.model.dto.AuthorityDTO;
import com.greedy.erp_bomb.member.model.dto.MemberDTO;
import com.greedy.erp_bomb.member.model.dto.MemberRoleDTO;
import com.greedy.erp_bomb.member.model.dto.MemberRolePk;

@Repository
public class AuthorityDAO {

	@PersistenceContext
	private EntityManager em;

	public List<MemberDTO> findMemberList() {
		String jpql = "SELECT a FROM MemberDTO a ORDER BY a.name";
		
		List<MemberDTO> memberList = em.createQuery(jpql, MemberDTO.class).getResultList();
		
		for (MemberDTO mem : memberList) {
			mem.getMemberRoleList().size();
		}
		
		return memberList;
	}

	public MemberDTO findMemberDetail(String detailName) {
		MemberDTO memberRole = em.find(MemberDTO.class, detailName);
		return memberRole;
	}

	public void updateAuth(String[] role, String name) {
		MemberDTO member = em.find(MemberDTO.class, name);
		
		String jpql = "SELECT a FROM MemberRoleDTO a WHERE a.member.name = :name";
		
		List<MemberRoleDTO> roleList = em.createQuery(jpql, MemberRoleDTO.class)
				                         .setParameter("name", name)
				                         .getResultList();
		
		for (MemberRoleDTO mr : roleList) {
			em.remove(mr);
		}
		
		em.flush();
		
		if (role != null) {
			for (String str : role) {
				System.out.println("뇨내 : " + str);
				em.persist(new MemberRoleDTO(em.find(AuthorityDTO.class, Integer.valueOf(str)), member));
			}
		}
			
	}

	public List<AuthorityDTO> findAllAuth() {
		String jpql = "SELECT a FROM AuthorityDTO a";
		
		List<AuthorityDTO> authList = em.createQuery(jpql, AuthorityDTO.class).getResultList();
		
		for (AuthorityDTO auth : authList) {
			auth.setAuthenticatedMenuList(null);
			auth.setMemberRoleList(null);
		}
		
		return authList;
	}


}
