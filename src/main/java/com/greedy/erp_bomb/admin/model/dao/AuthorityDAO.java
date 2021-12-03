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

	public MemberDTO findAuthDetail(String detailName) {
		MemberDTO memberRole = em.find(MemberDTO.class, detailName);
		return memberRole;
	}

	public void updateAuth(String[] role, String name) {
		MemberDTO member = em.find(MemberDTO.class, name);
		
		String jpql = "SELECT a FROM MemberRoleDTO a WHERE a.member.name = :name";
		
		List<MemberRoleDTO> roleList = em.createQuery(jpql, MemberRoleDTO.class)
				                         .setParameter("name", name)
				                         .getResultList();
		
//		if (roleList.size() == 2) {						
//			MemberRolePk pk1 = new MemberRolePk();
//			pk1.setMember(name);
//			pk1.setAuthority(1);
//			
//			MemberRolePk pk2 = new MemberRolePk();
//			pk2.setMember(name);
//			pk2.setAuthority(2);
//			
//			MemberRoleDTO memberRole1 = em.find(MemberRoleDTO.class, pk1);
//			MemberRoleDTO memberRole2 = em.find(MemberRoleDTO.class, pk2);
//			
//			em.remove(memberRole1);
//			em.remove(memberRole2);
//			
//			AuthorityDTO auth = em.find(AuthorityDTO.class, Integer.valueOf(role));
//			
//			em.persist(new MemberRoleDTO(auth, member));
//		}
		
//		if (role.equals(1 + "") && roleList.size() == 1) {
//			MemberRolePk pk1 = new MemberRolePk();
//			pk1.setMember(name);
//			pk1.setAuthority(2);
//			
//			MemberRoleDTO memberRole1 = em.find(MemberRoleDTO.class, pk1);
//			
//			em.remove(memberRole1);
//			
//			AuthorityDTO auth1 = em.find(AuthorityDTO.class, 1);
//			AuthorityDTO auth2 = em.find(AuthorityDTO.class, 2);
//			
//			em.persist(new MemberRoleDTO(auth1, member));
//			em.persist(new MemberRoleDTO(auth2, member));
//		}
//		
//		if (role.equals(2 + "") && roleList.size() == 1) {
//			MemberRolePk pk1 = new MemberRolePk();
//			pk1.setMember(name);
//			pk1.setAuthority(1);
//			
//			MemberRoleDTO memberRole1 = em.find(MemberRoleDTO.class, pk1);
//			
//			em.remove(memberRole1);
//			
//			AuthorityDTO auth1 = em.find(AuthorityDTO.class, 1);
//			AuthorityDTO auth2 = em.find(AuthorityDTO.class, 2);
//			
//			em.persist(new MemberRoleDTO(auth1, member));
//			em.persist(new MemberRoleDTO(auth2, member));
//		}
		
		for (MemberRoleDTO mr : roleList) {
			em.remove(mr);
		}
		
		em.flush();
		
		if (role != null) {
			for (String str : role) {
				em.persist(new MemberRoleDTO(em.find(AuthorityDTO.class, Integer.valueOf(str)), member));
			}
		}
			
	}

}
