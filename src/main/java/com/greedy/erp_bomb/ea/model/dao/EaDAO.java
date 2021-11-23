package com.greedy.erp_bomb.ea.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.greedy.erp_bomb.ea.model.dto.EADTO;
import com.greedy.erp_bomb.ea.model.dto.EAPathDTO;

@Repository
public class EaDAO {
	
	@PersistenceContext
	private EntityManager em;

	public EADTO test() {
		return em.find(EADTO.class, 3);
	}

	public List<EADTO> findMyEa(String userName) {
		String jpql = "SELECT a FROM EADTO as a WHERE a.member.name = :name";
		
		List<EADTO> myEaList = em.createQuery(jpql, EADTO.class).setParameter("name", userName).getResultList();
		
		for(EADTO ea : myEaList) {
			ea.getAddendumList().size();
			ea.getEaApprovalPathList().size();
			ea.getEaApprovalPathList().size();
		}
		
		return myEaList;
	}

	public List<EADTO> findEaPathList(String userName) {
		String jpql = "SELECT a FROM EA_APPROVAL_PATH as a WHERE a.member.name = :name";
		
		List<EAPathDTO> myEaPathList = em.createQuery(jpql, EAPathDTO.class).setParameter("name", userName).getResultList();
		
		List<EADTO> eaPathList = new ArrayList<>();
		
		for(EAPathDTO eaPath : myEaPathList) {
			eaPath.getEa().getAddendumList().size();
			eaPath.getEa().getEaApprovalPathList().size();
			eaPath.getEa().getEaApprovalPathList().size();
			eaPathList.add(eaPath.getEa());
		}
		
		return eaPathList;
	}

}
