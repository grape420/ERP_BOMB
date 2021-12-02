package com.greedy.erp_bomb.tna.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greedy.erp_bomb.tna.model.dao.TNADAO;
import com.greedy.erp_bomb.tna.model.dto.TNADTO;

@Service
public class TNAService {
	
	private TNADAO tnaDAO;
	
	@Autowired
	public TNAService(TNADAO tnaDAO) {
		this.tnaDAO = tnaDAO;
	}
	
	@Transactional
	public List<TNADTO> tnaDateSearch(String find) {
		return tnaDAO.tnaDateSearch(find);
	}

	@Transactional
	public List<TNADTO> selectDetail(String name) {
		return tnaDAO.selectDetail(name);
	}
	
	@Transactional
	public void regiTna(TNADTO tna) {
		tnaDAO.regiTna(tna);
		
	}

	@Transactional
	public void deletWork(TNADTO tna) {
		tnaDAO.deletWork(tna);
	}

	@Transactional
	public void newWork(TNADTO tna) {
		tnaDAO.newWork(tna);
	}

}
