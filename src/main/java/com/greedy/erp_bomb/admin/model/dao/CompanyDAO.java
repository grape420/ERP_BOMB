package com.greedy.erp_bomb.admin.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.greedy.erp_bomb.inventory.model.dto.CompanyDTO;
import com.greedy.erp_bomb.inventory.model.dto.IceCreamDTO;
import com.greedy.erp_bomb.inventory.model.dto.InventoryDTO;
import com.greedy.erp_bomb.inventory.model.dto.InventoryPk;
import com.greedy.erp_bomb.member.model.dto.MemberDTO;

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
		em.persist(company);
	}

	public List<IceCreamDTO> findIceList() {
		String jpql = "SELECT a FROM IceCreamDTO a";
		
		List<IceCreamDTO> iceList = em.createQuery(jpql, IceCreamDTO.class).getResultList();
		
		return iceList;
	}

	public void registNewInven(InventoryDTO inven, List<IceCreamDTO> iceList) {
		inven.setCompany(em.find(CompanyDTO.class, inven.getCompany().getSerialNo()));
		
		for (IceCreamDTO ice : iceList) {
			System.out.println("ice : " + ice);
			inven.setIceCream(ice);
			System.out.println("inven : " + inven);
		}
		em.persist(inven);
		
	}


}
