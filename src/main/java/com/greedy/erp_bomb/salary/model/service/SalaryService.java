package com.greedy.erp_bomb.salary.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		System.out.println("==========salaryService===========");
		System.out.println(userName);
		return salaryDAO.findAllMySalary(userName);
	}

	@Transactional
	public List<SalaryDTO> findAllSalary() {
		return salaryDAO.findAllSalary();
	}

	@Transactional
	public void registNewSalary(SalaryDTO newSalary) {
		System.out.println("==========newsalaryService===========");
		System.out.println(newSalary);
		
		salaryDAO.registNewSalary(newSalary);
	}
	
}
