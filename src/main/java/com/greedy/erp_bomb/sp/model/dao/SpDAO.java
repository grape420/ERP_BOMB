package com.greedy.erp_bomb.sp.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.greedy.erp_bomb.member.model.dto.MemberDTO;
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

	public void newRegistSp(SPDTO sp) {
		
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

	public List<MemberDTO> findMemberList() {
		String jpql = "SELECT m FROM MemberDTO as m ORDER BY a.rank.no DESC";
		return em.createQuery(jpql, MemberDTO.class).getResultList();
	}
	
	public MemberDTO findMemberInfo(String name) {
		return em.find(MemberDTO.class, name);
	}

	public void updateSp(SPDTO sp2) {
		MemberDTO member = new MemberDTO();
		member.setName(sp2.getMember().getName());
		
		MemberDTO selectMem = em.find(MemberDTO.class, member.getName());
		
		selectMem.getSp().setEmpYear(sp2.getEmpYear());
		selectMem.getSp().setServerancePay(sp2.getServerancePay());
		
	}

	public List<MemberDTO> findEntryMember() {
		String jpql = "SELECT a FROM MemberDTO as a";
		
		List<MemberDTO> memberList = em.createQuery(jpql, MemberDTO.class).getResultList(); 
		
		List<MemberDTO> spList = new ArrayList<>();
		for (MemberDTO member : memberList) {
			
			if(member.getSp() == null) {
				spList.add(member);
			}
		}
		
		return spList;
	}



	
}
