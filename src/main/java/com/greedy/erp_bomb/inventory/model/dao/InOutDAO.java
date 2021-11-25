package com.greedy.erp_bomb.inventory.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.greedy.erp_bomb.inventory.model.dto.CompanyDTO;
import com.greedy.erp_bomb.inventory.model.dto.IceCreamDTO;
import com.greedy.erp_bomb.inventory.model.dto.InOutDTO;
import com.greedy.erp_bomb.inventory.model.dto.InventoryDTO;
import com.greedy.erp_bomb.inventory.model.dto.InventoryPk;

@Repository
public class InOutDAO {
	
	@PersistenceContext
	private EntityManager em;

	public List<InOutDTO> findInOutList() {
		String jpql = "SELECT m FROM InOutDTO as m ORDER BY m.no";
		
		List<InOutDTO> inOutList = em.createQuery(jpql, InOutDTO.class).getResultList();
		
		return inOutList;
	}

	public List<IceCreamDTO> findIcecreamList() {
		String jpql = "SELECT m FROM IceCreamDTO as m";
		
		List<IceCreamDTO> icecreamList = em.createQuery(jpql, IceCreamDTO.class).getResultList();
		
		for (IceCreamDTO ice : icecreamList) {
			ice.setInventoryList(null);
		}
		
		return icecreamList;
	}

	public void registInOut(InOutDTO inOut) {
		inOut.getInventory().setCompany(em.find(CompanyDTO.class, inOut.getInventory().getCompany().getSerialNo()));
		inOut.getInventory().setIceCream(em.find(IceCreamDTO.class, inOut.getInventory().getIceCream().getNo()));
		
		em.persist(inOut);
	}

	public void updateInventory(InventoryDTO inven) {
		InventoryPk pk = new InventoryPk();
		pk.setCompany(inven.getCompany().getSerialNo());
		pk.setIceCream(inven.getIceCream().getNo());
		
		InventoryDTO selectedInven = em.find(InventoryDTO.class, pk);
		selectedInven.setInvenRemainStock(inven.getInvenRemainStock());
	}

	public InventoryDTO findInven(InventoryPk inventoryPk) {
		return em.find(InventoryDTO.class, inventoryPk);
	}

}
