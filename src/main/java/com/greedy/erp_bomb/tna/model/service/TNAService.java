package com.greedy.erp_bomb.tna.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.erp_bomb.tna.model.dao.TNADAO;

@Service
public class TNAService {
	
	private TNADAO tnaDAO;
	
	@Autowired
	public TNAService(TNADAO tnaDAO) {
		this.tnaDAO = tnaDAO;
	}

}
