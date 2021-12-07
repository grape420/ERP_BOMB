package com.greedy.erp_bomb.salary.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.greedy.erp_bomb.member.model.dto.MemberDTO;
import com.greedy.erp_bomb.salary.model.dto.SalaryDTO;

@Repository
public class SalaryDAO {

	@PersistenceContext	
	private EntityManager em;

	/* 본인의 급여 목록 불러오기 */
	public List<SalaryDTO> findAllMySalary(String userName) {
		String jpql = "SELECT s FROM SalaryDTO as s WHERE s.member.name = :name ORDER BY s.serialNo DESC";
		
		TypedQuery<SalaryDTO> query = em.createQuery(jpql, SalaryDTO.class)
										.setParameter("name", userName);
		
		List<SalaryDTO> salaryList = query.getResultList();
		
		for(SalaryDTO s : salaryList) {
			System.out.println(s);
		}
		
		return salaryList;	
		}

	/* 모든 급여 목록 불러오기 */
	public List<SalaryDTO> findAllSalary() {
		String jpql = "SELECT s FROM SalaryDTO as s ORDER BY s.serialNo DESC";
		
		TypedQuery<SalaryDTO> query = em.createQuery(jpql, SalaryDTO.class);
		
		List<SalaryDTO> allSalaryList = query.getResultList();
		
		for(SalaryDTO s : allSalaryList) {
			System.out.println(s);
		}
		
		return allSalaryList;		
		}

	/* member 전체 조회하기 */
	public List<MemberDTO> findMemberList() {
		String jpql = "SELECT a FROM MemberDTO as a WHERE a.entYn = 'N' ORDER BY a.rank.no DESC";
		return em.createQuery(jpql, MemberDTO.class).getResultList();
	}
	
	/* name을 전달 받아 member 조회하기 */
	public MemberDTO findMemberInfo(String name) {
		return em.find(MemberDTO.class, name);
	}
	
	/* 급여 상세 추가하기 */
	public void registNewSalary(SalaryDTO newSalary) {
		em.persist(newSalary);
	}
}
