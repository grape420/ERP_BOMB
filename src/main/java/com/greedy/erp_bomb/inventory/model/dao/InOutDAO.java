package com.greedy.erp_bomb.inventory.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.greedy.erp_bomb.inventory.model.dto.CompanyDTO;
import com.greedy.erp_bomb.inventory.model.dto.InOutDTO;

@Repository
public class InOutDAO {
	
	@PersistenceContext
	private EntityManager em;

	public List<InOutDTO> findInOutList() {
		String jpql = "SELECT m FROM InOutDTO as m ORDER BY m.no";
		
		List<InOutDTO> inOutList = em.createQuery(jpql, InOutDTO.class).getResultList();
		
		return inOutList;
	}

	public List<CompanyDTO> findCompanyList() {
		String jpql = "SELECT m FROM CompanyDTO as m ORDER BY m.serialNo";
		
		List<CompanyDTO> companyList = em.createQuery(jpql, CompanyDTO.class).getResultList();
		
		for(CompanyDTO com : companyList) {
			com.setInventoryList(null);
			com.setMemberList(null);
		}
		
		return companyList;
	}

}
