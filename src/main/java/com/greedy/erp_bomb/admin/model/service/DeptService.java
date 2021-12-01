package com.greedy.erp_bomb.admin.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.erp_bomb.admin.model.dao.DeptDAO;
import com.greedy.erp_bomb.member.model.dto.DeptDTO;

@Service
public class DeptService {
	private DeptDAO deptDAO;
	
	@Autowired
	public DeptService(DeptDAO deptDAO) {
		this.deptDAO = deptDAO;
	}

	@Transactional
	public List<DeptDTO> findDeptList() {
		return deptDAO.findDeptList();
	}

	@Transactional
	public void registNewDept(DeptDTO dept) {
		deptDAO.registNewDept(dept);
	}

}
