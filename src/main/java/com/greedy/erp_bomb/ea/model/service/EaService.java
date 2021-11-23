package com.greedy.erp_bomb.ea.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greedy.erp_bomb.ea.model.dao.EaDAO;
import com.greedy.erp_bomb.ea.model.dto.EADTO;

@Service
public class EaService {
	
	private EaDAO eaDAO;
	
	@Autowired
	public EaService(EaDAO eaDAO) {
		this.eaDAO = eaDAO;
	}

	@Transactional
	public EADTO test() {
		return eaDAO.test();
	}
	
	@Transactional
	public List<EADTO> findMyEa(String userName) {
		return eaDAO.findMyEa(userName);
	}

	public List<EADTO> findEaPathList(String userName) {
		return eaDAO.findEaPathList(userName);
	}
}
