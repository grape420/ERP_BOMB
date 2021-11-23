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
	public List<SalaryDTO> findAllMySalary() {
		return salaryDAO.findAllMySalary();
	}
	
//	@Transactional
//	public void registNewMenu(SalaryDTO newSalary) {
//		salaryDAO.registNewMenu(newSalary);
//	}
//	
//	@Transactional
//	public void modifySalary(SalaryDTO salary) {
//		salaryDAO.modifySalary(salary);
//	}
	
}
