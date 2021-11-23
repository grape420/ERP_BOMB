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
		String jpql = "SELECT s FROM CategoryDTO as c ORDER BY c.code ASC";
		
		TypedQuery<SalaryDTO> query = em.createQuery(jpql, SalaryDTO.class);
		List<SalaryDTO> menuList = query.getResultList();
		
		return menuList;	}

}
