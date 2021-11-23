package com.greedy.erp_bomb.salary.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.greedy.erp_bomb.salary.model.dto.SalaryDTO;

@Repository
public class SalaryDAO {

	@PersistenceContext	
	private EntityManager em;

	public List<SalaryDTO> findAllMySalary() {
		String jpql = "SELECT s FROM SalaryDTO as s ORDER BY s.serialNo ASC";
		
		TypedQuery<SalaryDTO> query = em.createQuery(jpql, SalaryDTO.class);
		List<SalaryDTO> salaryList = query.getResultList();
		
		System.out.println("=====================salaryDAO======================");
		for(SalaryDTO s : salaryList) {
			System.out.println(s);
		}
		
		return salaryList;	
		}

}
