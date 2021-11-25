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
import com.greedy.erp_bomb.member.model.dto.MemberDTO;

@Repository
public class InOutDAO {
	
	@PersistenceContext
	private EntityManager em;

	public List<InOutDTO> findInOutList(String name) {
		MemberDTO member = em.find(MemberDTO.class, name);
		member.getCompany().getSerialNo();
		
//		CompanyDTO com = em.find(CompanyDTO.class, 2);
//		for(InventoryDTO inven : com.getInventoryList()) {
//			System.out.println("인벤임 : " + inven);
//			for (InOutDTO inOut : inven.getInOutList()) {
//				System.out.println("인아웃임 : " + inOut);
//			}
//		}
		InOutDTO inOut = em.find(InOutDTO.class, 2);
		System.out.println("인벤인벤 : " + inOut.getInventory().getCompany());
		
		System.out.println("이름 뭐야 : " + member.getName());
		System.out.println("번호 뭐야 : " + member.getCompany().getSerialNo());
		
		String jpql = "SELECT m FROM InOutDTO as m WHERE m.inventory.company.serialNo = 2";
		
		List<InOutDTO> inOutList = em.createQuery(jpql, InOutDTO.class)
//				                     .setParameter("company", member.getCompany().getSerialNo())
				                     .getResultList();
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

	public InventoryDTO findHeadInven(InventoryPk pk2) {
		return em.find(InventoryDTO.class, pk2);
	}

	public void updateHeadInven(InventoryDTO headInven) {
		InventoryPk pk2 = new InventoryPk();
		pk2.setCompany(1);
		pk2.setIceCream(headInven.getIceCream().getNo());
		
		InventoryDTO selectedInven = em.find(InventoryDTO.class, pk2);
		selectedInven.setInvenRemainStock(headInven.getInvenRemainStock());
	}


}
