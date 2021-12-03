package com.greedy.erp_bomb.admin.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.greedy.erp_bomb.admin.model.dao.AuthorityDAO;
import com.greedy.erp_bomb.member.model.dto.AuthorityDTO;
import com.greedy.erp_bomb.member.model.dto.MemberDTO;
import com.greedy.erp_bomb.member.model.dto.MemberRoleDTO;

@Service
public class AuthorityService {
	
	private AuthorityDAO authorityDAO;
	
	public AuthorityService(AuthorityDAO authorityDAO) {
		this.authorityDAO = authorityDAO;
	}

	@Transactional
	public List<MemberDTO> findMemberList() {
		return authorityDAO.findMemberList();
	}

	@Transactional
	public MemberDTO findMemberDetail(String detailName) {
		return authorityDAO.findMemberDetail(detailName);
	}
	
	@Transactional
	public void updateAuth(String[] role, String name) {
		authorityDAO.updateAuth(role, name);
	}

	@Transactional
	public List<AuthorityDTO> findAllAuth() {
		return authorityDAO.findAllAuth();
	}


	
}
