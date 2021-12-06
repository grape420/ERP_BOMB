package com.greedy.erp_bomb.admin.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.greedy.erp_bomb.inventory.model.dto.CompanyDTO;
import com.greedy.erp_bomb.inventory.model.dto.IceCreamDTO;
import com.greedy.erp_bomb.inventory.model.dto.InventoryDTO;

@Repository
public class IcecreamDAO {

	@PersistenceContext
	private EntityManager em;

	public List<IceCreamDTO> findIcecreamList() {
		String jpql = "SELECT a FROM IceCreamDTO a";
		
		List<IceCreamDTO> icecreamList = em.createQuery(jpql, IceCreamDTO.class).getResultList();
		
		return icecreamList;
	}

	public void registNewIce(IceCreamDTO ice) {
		em.persist(ice);
		
		String jpql = "SELECT a FROM CompanyDTO a ORDER BY a.serialNo";
		
		List<CompanyDTO> comList = em.createQuery(jpql, CompanyDTO.class).getResultList();
		
		for (CompanyDTO com : comList) {
			em.persist(new InventoryDTO(ice, com, 0, null));
		}
	}
}
