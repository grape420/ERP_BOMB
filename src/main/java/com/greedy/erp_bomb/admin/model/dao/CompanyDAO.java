package com.greedy.erp_bomb.admin.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.greedy.erp_bomb.inventory.model.dto.CompanyDTO;
import com.greedy.erp_bomb.inventory.model.dto.IceCreamDTO;
import com.greedy.erp_bomb.inventory.model.dto.InventoryDTO;

@Repository
public class CompanyDAO {

	@PersistenceContext
	private EntityManager em;

	public List<CompanyDTO> findCompanyList() {
		String jpql = "SELECT a FROM CompanyDTO a";
		
		List<CompanyDTO> companyList = em.createQuery(jpql, CompanyDTO.class).getResultList();
		
		return companyList;
	}

	public void registNewCompany(CompanyDTO company) {
		
		/* 회사 추가 */
		em.persist(company);
		
		String jpql = "SELECT a FROM IceCreamDTO a";
		
		/* 모든 아이스크림 리스트 */
		List<IceCreamDTO> iceList = em.createQuery(jpql, IceCreamDTO.class).getResultList();
		
		for (IceCreamDTO ice : iceList) {
			
			/* 새로운 InventoryDTO에 추가된 회사와 모든 아이스크림 리스트 추가 */
			em.persist(new InventoryDTO(ice, company, 0, null));
		}
	}

	public CompanyDTO comDetail(int detailNum) {
		CompanyDTO com = em.find(CompanyDTO.class, detailNum);
		com.setInventoryList(null);
		com.setMemberList(null);
		
		return com;
	}

	public void updateCompany(CompanyDTO company) {
		CompanyDTO com = em.find(CompanyDTO.class, company.getSerialNo());
		com.setStatus(company.getStatus());
	}


}
