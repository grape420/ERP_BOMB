package com.greedy.erp_bomb.ea.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.greedy.erp_bomb.ea.model.dto.EACarbonDTO;
import com.greedy.erp_bomb.ea.model.dto.EADTO;
import com.greedy.erp_bomb.ea.model.dto.EAPathDTO;
import com.greedy.erp_bomb.member.model.dto.MemberDTO;

@Repository
public class EaDAO {
	
	@PersistenceContext
	private EntityManager em;

	public List<EADTO> findMyEa(String userName) {
		String jpql = "SELECT a FROM EADTO as a WHERE a.member.name = :name";
		
		List<EADTO> myEaList = em.createQuery(jpql, EADTO.class).setParameter("name", userName).getResultList();
		
		for(EADTO ea : myEaList) {
			ea.getAddendumList().size();
			ea.getEaApprovalPathList().size();
			ea.getEaCarbonList().size();
			ea.getMember().getName();
			for(EAPathDTO eaPath : ea.getEaApprovalPathList()) {
				eaPath.getMember().getRank();
			}
		}
		
		return myEaList;
	}

	public List<EADTO> findEaPathList(String userName) {
		String jpql = "SELECT a FROM EAPathDTO as a WHERE a.member.name = :name";
		
		List<EAPathDTO> myEaPathList = em.createQuery(jpql, EAPathDTO.class).setParameter("name", userName).getResultList();
		
		List<EADTO> eaPathList = new ArrayList<>();
		
		for(EAPathDTO eaPath : myEaPathList) {
			eaPath.getEa().getAddendumList().size();
			eaPath.getEa().getEaApprovalPathList().size();
			eaPath.getEa().getEaCarbonList().size();
			eaPathList.add(eaPath.getEa());
		}
		
		return eaPathList;
	}

	public List<EADTO> findEaCarbonList(String userName) {
		String jpql = "SELECT a FROM EACarbonDTO as a WHERE a.member.name = :name";
		
		List<EACarbonDTO> myEaPathList = em.createQuery(jpql, EACarbonDTO.class).setParameter("name", userName).getResultList();
		
		List<EADTO> eaCarbonList = new ArrayList<>();
		
		for(EACarbonDTO eaCarbon : myEaPathList) {
			eaCarbon.getEa().getAddendumList().size();
			eaCarbon.getEa().getEaApprovalPathList().size();
			eaCarbon.getEa().getEaCarbonList().size();
			eaCarbonList.add(eaCarbon.getEa());
		}
		
		return eaCarbonList;
	}

	public List<MemberDTO> findMemberList() {
		String jpql = "SELECT a FROM MemberDTO as a ORDER BY a.rank.no DESC";
		return em.createQuery(jpql, MemberDTO.class).getResultList();
	}

	public void insertEa(EADTO ea) {
		ea.setMember(em.find(MemberDTO.class, ea.getMember().getName()));
		
		for(EAPathDTO eaPath : ea.getEaApprovalPathList()) {
			eaPath.setMember(em.find(MemberDTO.class, eaPath.getMember().getName()));
		}
		
		for(EACarbonDTO eaCarbon : ea.getEaCarbonList()) {
			eaCarbon.setMember(em.find(MemberDTO.class, eaCarbon.getMember().getName()));
		}
		
		em.persist(ea);
	}

}
