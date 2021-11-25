package com.greedy.erp_bomb.inventory.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.greedy.erp_bomb.inventory.model.dto.CompanyDTO;
import com.greedy.erp_bomb.inventory.model.dto.IceCreamDTO;
import com.greedy.erp_bomb.inventory.model.dto.InventoryDTO;
import com.greedy.erp_bomb.inventory.model.dto.InventoryPk;

@Repository
public class InventoryDAO {
	
	@PersistenceContext
	private EntityManager em;

	public List<InventoryDTO> findInvenList() {
		String jpql = "SELECT m FROM InventoryDTO as m";
		
		List<InventoryDTO> invenList = em.createQuery(jpql, InventoryDTO.class).getResultList();
		
		return invenList;
	}

	public List<CompanyDTO> findCompanyList() {
		String jpql = "SELECT DISTINCT m FROM CompanyDTO as m ORDER BY m.serialNo";
		
		List<CompanyDTO> companyList = em.createQuery(jpql, CompanyDTO.class).getResultList();
		
		for(CompanyDTO com : companyList) {
			com.setInventoryList(null);
			com.setMemberList(null);
			System.out.println("여기는 DAO company : " + com);
		}
		
		return companyList;
	}

	public List<IceCreamDTO> findIcecreamList() {
		String jpql = "SELECT DISTINCT m FROM IceCreamDTO as m ORDER BY m.no";
		
		List<IceCreamDTO> icecreamList = em.createQuery(jpql, IceCreamDTO.class).getResultList();
		
		for (IceCreamDTO ice : icecreamList) {
			ice.setInventoryList(null);
			System.out.println("여기는 DAO icecream : " + ice);
		}
		
		return icecreamList;
	}

	public void registNewIce(IceCreamDTO ice) {
		em.persist(ice);
	}

	public void registInven(InventoryDTO inven) {
		inven.setCompany(em.find(CompanyDTO.class, inven.getCompany().getSerialNo()));
		inven.setIceCream(em.find(IceCreamDTO.class, inven.getIceCream().getNo()));
		
		em.persist(inven);
		
//		InventoryPk pk = new InventoryPk();
//		pk.setCompany(inven.getCompany().getSerialNo());
//		pk.setIceCream(inven.getIceCream().getNo());
//		
//		InventoryDTO selectedInven = em.find(InventoryDTO.class, pk);
//		selectedInven.setInvenRemainStock(inven.getInvenRemainStock());
		
	}
	
}
