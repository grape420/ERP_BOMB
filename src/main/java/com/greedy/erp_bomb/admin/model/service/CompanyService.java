package com.greedy.erp_bomb.admin.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.erp_bomb.admin.model.dao.CompanyDAO;
import com.greedy.erp_bomb.inventory.model.dto.CompanyDTO;
import com.greedy.erp_bomb.inventory.model.dto.IceCreamDTO;
import com.greedy.erp_bomb.inventory.model.dto.InventoryDTO;
import com.greedy.erp_bomb.inventory.model.dto.InventoryPk;

@Service
public class CompanyService {

	private CompanyDAO companyDAO;
	
	@Autowired
	public CompanyService(CompanyDAO companyDAO) {
		this.companyDAO = companyDAO;
	}

	@Transactional
	public List<CompanyDTO> findCompanyList() {
		return companyDAO.findCompanyList();
	}

	@Transactional
	public void registNewCompany(CompanyDTO company) {
		companyDAO.registNewCompany(company);
	}

	@Transactional
	public CompanyDTO comDetail(int detailNum) {
		return companyDAO.comDetail(detailNum);
	}

	@Transactional
	public void updateCompany(CompanyDTO company) {
		companyDAO.updateCompany(company);
	}



}
