package com.greedy.erp_bomb.admin.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.greedy.erp_bomb.inventory.model.dto.CompanyDTO;
import com.greedy.erp_bomb.member.model.dto.AuthorityDTO;
import com.greedy.erp_bomb.member.model.dto.DeptDTO;
import com.greedy.erp_bomb.member.model.dto.MemberDTO;
import com.greedy.erp_bomb.member.model.dto.MemberRoleDTO;
import com.greedy.erp_bomb.member.model.dto.MemberRolePk;
import com.greedy.erp_bomb.member.model.dto.RankDTO;

@Repository
public class AdminMemberDAO {

	@PersistenceContext
	private EntityManager em;

	public List<MemberDTO> findMemberList() {
		String jpql = "SELECT a FROM MemberDTO a";
		
		List<MemberDTO> memberList = em.createQuery(jpql, MemberDTO.class).getResultList();
		
		return memberList;
	}

	public List<CompanyDTO> findCompanyList() {
		String jpql = "SELECT m FROM CompanyDTO as m ORDER BY m.serialNo";
		
		List<CompanyDTO> companyList = em.createQuery(jpql, CompanyDTO.class).getResultList();
		
		for(CompanyDTO com : companyList) {
			com.setInventoryList(null);
			com.setMemberList(null);
		}
		
		return companyList;
	}

	public List<DeptDTO> findDeptCodeList() {
		String jpql = "SELECT a FROM DeptDTO a ORDER BY a.no";
		
		List<DeptDTO> deptList = em.createQuery(jpql, DeptDTO.class).getResultList();
		
		for (DeptDTO dept : deptList) {
			dept.setMemberList(null);
		}
		
		return deptList;
	}

	public List<RankDTO> findRankCodeList() {
		String jpql = "SELECT a FROM RankDTO a ORDER BY a.no";
		
		List<RankDTO> rankList = em.createQuery(jpql, RankDTO.class).getResultList();
		
		for (RankDTO rank : rankList) {
			rank.setMemberList(null);
		}
		
		return rankList;
	}

	public MemberDTO selectMemberByName(String value) {
		MemberDTO member = em.find(MemberDTO.class, value);
		System.out.println("DAO member : " + member);
		
		return member;
	}

	public void registMember(MemberDTO member) {
		member.getMemberRoleList().add(new MemberRoleDTO(em.find(AuthorityDTO.class, 1), member));
		
		em.persist(member);
	}

	public MemberDTO memDetail(String detailName) {
		MemberDTO member = em.find(MemberDTO.class, detailName);
		
		return member;
	}

	public void updateMem(MemberDTO member) {
		MemberDTO mem = em.find(MemberDTO.class, member.getName());
		mem.setCompany(member.getCompany());
		mem.setDept(member.getDept());
		mem.setRank(member.getRank());
		mem.setPwd(member.getPwd());
		mem.setEmail(member.getEmail());
		mem.setEntYn(member.getEntYn());
		mem.setPhone(member.getPhone());
	}
}
