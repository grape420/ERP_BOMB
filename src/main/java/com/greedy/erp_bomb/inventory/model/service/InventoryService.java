package com.greedy.erp_bomb.inventory.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.erp_bomb.inventory.model.dao.InventoryDAO;
import com.greedy.erp_bomb.inventory.model.dto.CompanyDTO;
import com.greedy.erp_bomb.inventory.model.dto.IceCreamDTO;
import com.greedy.erp_bomb.inventory.model.dto.InventoryDTO;

@Service
public class InventoryService {
	
	private InventoryDAO inventoryDAO;
	
	@Autowired
	public InventoryService(InventoryDAO inventoryDAO) {
		this.inventoryDAO = inventoryDAO;
	}

	@Transactional
	public List<InventoryDTO> findInvenList() {
		return inventoryDAO.findInvenList();
	}

	@Transactional
	public List<CompanyDTO> findCompanyList() {
		return inventoryDAO.findCompanyList();
	}

	@Transactional
	public List<IceCreamDTO> findIcecreamList() {
		return inventoryDAO.findIcecreamList();
	}

	@Transactional
	public void registNewIce(IceCreamDTO ice) {
		inventoryDAO.registNewIce(ice);
	}

	@Transactional
	public void registInven(InventoryDTO inven) {
		inventoryDAO.registInven(inven);
	}

//	@Transactional
//	public List<InventoryDTO> searchInven(String keyword) {
//		return inventoryDAO.searchInven(keyword);
//	}

}
