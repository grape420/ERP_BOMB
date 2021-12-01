package com.greedy.erp_bomb.salary.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.erp_bomb.member.model.dto.MemberDTO;
import com.greedy.erp_bomb.salary.model.dao.SalaryDAO;
import com.greedy.erp_bomb.salary.model.dto.SalaryDTO;

@Service
public class SalaryService {
	
	private SalaryDAO salaryDAO;
	
	@Autowired
	public SalaryService(SalaryDAO salaryDAO) {
		this.salaryDAO = salaryDAO;
	}
	
	@Transactional
	public List<SalaryDTO> findAllMySalary(String userName) {
		return salaryDAO.findAllMySalary(userName);
	}

	@Transactional
	public List<SalaryDTO> findAllSalary() {
		return salaryDAO.findAllSalary();
	}

	@Transactional
	public List<MemberDTO> findMemberList() {
		return salaryDAO.findMemberList();
	}
	
	@Transactional
	public void registNewSalary(SalaryDTO newSalary) {
		salaryDAO.registNewSalary(newSalary);
	}

	@Transactional
	public MemberDTO findMemberInfo(String name) {
		
		System.out.println("============Service==============");
		System.out.println("DTO : " + salaryDAO.findMemberInfo(name));
		return salaryDAO.findMemberInfo(name);
	}
}
