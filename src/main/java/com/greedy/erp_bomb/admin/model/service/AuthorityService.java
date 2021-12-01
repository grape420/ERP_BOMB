package com.greedy.erp_bomb.admin.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.greedy.erp_bomb.admin.model.dao.AuthorityDAO;
import com.greedy.erp_bomb.member.model.dto.MemberRoleDTO;

@Service
public class AuthorityService {
	
	private AuthorityDAO authorityDAO;
	
	public AuthorityService(AuthorityDAO authorityDAO) {
		this.authorityDAO = authorityDAO;
	}

	@Transactional
	public List<MemberRoleDTO> findAuthorityList() {
		return authorityDAO.findAuthorityList();
	}

	@Transactional
	public MemberRoleDTO findAuthDetail(String detailName) {
		return authorityDAO.findAuthDetail(detailName);
	}


}
