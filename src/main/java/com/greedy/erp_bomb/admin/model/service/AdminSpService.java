package com.greedy.erp_bomb.admin.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.erp_bomb.admin.model.dao.AdminSpDAO;
import com.greedy.erp_bomb.member.model.dto.MemberDTO;
import com.greedy.erp_bomb.sp.model.dto.SPDTO;

@Service
public class AdminSpService {
	
	private AdminSpDAO adminSpDAO;
	
	@Autowired
	public AdminSpService(AdminSpDAO adminSpDAO) {
		this.adminSpDAO = adminSpDAO;
	}
	
	@Transactional
	public List<SPDTO> findSpList() {
		return adminSpDAO.findSpList();
	}
	
	@Transactional
	public List<MemberDTO> findEntryMember() {
		return adminSpDAO.findEntryMember();
	}

	@Transactional
	public MemberDTO findMemberInfo(String name) {
		return adminSpDAO.findMemberInfo(name);
	}

	@Transactional
	public void newRegistSp(SPDTO sp) {
		adminSpDAO.newRegistSp(sp);
	}

	public void updateSp(SPDTO sp2) {
		adminSpDAO.updateSp(sp2);
	}

}
