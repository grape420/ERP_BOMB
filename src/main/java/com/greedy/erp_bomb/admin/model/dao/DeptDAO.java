package com.greedy.erp_bomb.admin.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.greedy.erp_bomb.member.model.dto.DeptDTO;

@Repository
public class DeptDAO {

	@PersistenceContext
	private EntityManager em;

	public List<DeptDTO> findDeptList() {
		String jpql = "SELECT a FROM DeptDTO a";
		
		List<DeptDTO> deptList = em.createQuery(jpql, DeptDTO.class).getResultList();
		
		return deptList;
	}

	public void registNewDept(DeptDTO dept) {
		em.persist(dept);
	}
}
