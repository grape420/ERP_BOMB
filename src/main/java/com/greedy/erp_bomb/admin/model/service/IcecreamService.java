package com.greedy.erp_bomb.admin.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.erp_bomb.admin.model.dao.IcecreamDAO;
import com.greedy.erp_bomb.inventory.model.dto.IceCreamDTO;

@Service
public class IcecreamService {
	
	private IcecreamDAO icecreamDAO;
	
	@Autowired
	public IcecreamService(IcecreamDAO icecreamDAO) {
		this.icecreamDAO = icecreamDAO;
	}

	@Transactional
	public List<IceCreamDTO> findIcecreamList() {
		return icecreamDAO.findIcecreamList();
	}

	@Transactional
	public void registNewIce(IceCreamDTO ice) {
		icecreamDAO.registNewIce(ice);
	}

}
