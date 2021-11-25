package com.greedy.erp_bomb.inventory.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.erp_bomb.inventory.model.dao.InOutDAO;
import com.greedy.erp_bomb.inventory.model.dto.CompanyDTO;
import com.greedy.erp_bomb.inventory.model.dto.IceCreamDTO;
import com.greedy.erp_bomb.inventory.model.dto.InOutDTO;
import com.greedy.erp_bomb.inventory.model.dto.InventoryDTO;
import com.greedy.erp_bomb.inventory.model.dto.InventoryPk;

@Service
public class InOutService {
	
	private InOutDAO inOutDAO;
	
	@Autowired
	public InOutService(InOutDAO inOutDAO) {
		this.inOutDAO = inOutDAO;
	}

	@Transactional
	public List<InOutDTO> findInOutList() {
		return inOutDAO.findInOutList();
	}

	@Transactional
	public List<IceCreamDTO> findIcecreamList() {
		return inOutDAO.findIcecreamList();
	}

	@Transactional
	public void registInOut(InOutDTO inOut) {
		inOutDAO.registInOut(inOut);
	}

	@Transactional
	public void updateInventory(InventoryDTO inven) {
		inOutDAO.updateInventory(inven);
	}

	@Transactional
	public InventoryDTO findInven(InventoryPk inventoryPk) {
		return inOutDAO.findInven(inventoryPk);
	}


}
