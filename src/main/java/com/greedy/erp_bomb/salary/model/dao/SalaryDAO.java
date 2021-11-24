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

	/* 본인의 급여 목록 불러오기 */
	public List<SalaryDTO> findAllMySalary(String userName) {
		System.out.println("=====================salaryDAO======================");
		System.out.println(userName);
		
		String jpql = "SELECT s FROM SalaryDTO as s WHERE s.member.name = :name ORDER BY s.serialNo DESC";
		
		TypedQuery<SalaryDTO> query = em.createQuery(jpql, SalaryDTO.class)
										.setParameter("name", userName);
		
		List<SalaryDTO> salaryList = query.getResultList();
		
		for(SalaryDTO s : salaryList) {
			System.out.println(s);
		}
		
		return salaryList;	
		}

	
	/* 관리자만 */
	/* 모든 급여 목록 불러오기 */
	public List<SalaryDTO> findAllSalary() {
		System.out.println("=====================salaryDAO======================");
		
		String jpql = "SELECT s FROM SalaryDTO as s ORDER BY s.serialNo DESC";
		
		TypedQuery<SalaryDTO> query = em.createQuery(jpql, SalaryDTO.class);
		
		List<SalaryDTO> allSalaryList = query.getResultList();
		
		for(SalaryDTO s : allSalaryList) {
			System.out.println(s);
		}
		
		return allSalaryList;		
		}

	/* 급여 상세 수정하기 */
	
	/* 급여 상세 추가하기 */

}
