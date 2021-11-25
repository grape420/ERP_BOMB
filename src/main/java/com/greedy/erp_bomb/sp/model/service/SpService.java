package com.greedy.erp_bomb.sp.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.erp_bomb.member.model.dto.MemberDTO;
import com.greedy.erp_bomb.sp.model.dao.SpDAO;
import com.greedy.erp_bomb.sp.model.dto.SPDTO;

@Service
public class SpService {

	private SpDAO spDAO;
	
	
	@Autowired
	public SpService(SpDAO spDAO) {
		this.spDAO = spDAO;
	}
	
	@Transactional
	public List<SPDTO> findSpList() {
		
		return spDAO.findSpList();
	}
	
	@Transactional
	public void newRegistSp(SPDTO sp) {
		spDAO.newRegistSp(sp);
	}

	@Transactional
	public List<SPDTO> findDetailSp(String userName) {

		return spDAO.findDetailSp(userName);
	}
	
	
//	@Transactional
//	public List<MemberDTO> findMemberList() {
//		return spDAO.findMemberList();
//	}

}
