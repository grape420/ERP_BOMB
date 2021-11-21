package com.greedy.erp_bomb.member.model.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.greedy.erp_bomb.inventory.model.dto.CompanyDTO;

/* 스프링 시큐리티의 principal객체에서 더 구체적인 정보를 얻기 위해 확장된 User객체 생성용 클래스 작성 */
public class UserImpl extends User{
	private static final long serialVersionUID = -4664727421536588459L;
	
	private String name;
	private CompanyDTO company;
	private DeptDTO dept;
	private RankDTO rank;
	private String pwd;
	private int empNo;
	private String birth;
	private String phone;
	private java.sql.Date joinDate;
	private java.sql.Date quitDate;
	private int regularPay;
	private int bonus;
	private int annualIncome;
	private String email;
	
	private List<MemberRoleDTO> memberRoleList = new ArrayList<>();		// 회원별권한리스트
	
	public UserImpl(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	
	/* MemberDTO 객체를 전달 받아 필드를 다 초기화 해주는 메소드 작성 */
	public void setDetails(MemberDTO member) {
		this.name = member.getName();
		this.company = member.getCompany();
		this.dept = member.getDept();
		this.rank = member.getRank();
		this.pwd = member.getPwd();
		this.empNo = member.getEmpNo();
		this.birth = member.getBirth();
		this.phone = member.getPhone();
		this.joinDate = member.getJoinDate();
		this.quitDate = member.getQuitDate();
		this.regularPay = member.getRegularPay();
		this.bonus = member.getBonus();
		this.annualIncome = member.getAnnualIncome();
		this.email = member.getEmail();
		this.memberRoleList = member.getMemberRoleList();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CompanyDTO getCompany() {
		return company;
	}

	public void setCompany(CompanyDTO company) {
		this.company = company;
	}

	public DeptDTO getDept() {
		return dept;
	}

	public void setDept(DeptDTO dept) {
		this.dept = dept;
	}

	public RankDTO getRank() {
		return rank;
	}

	public void setRank(RankDTO rank) {
		this.rank = rank;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public java.sql.Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(java.sql.Date joinDate) {
		this.joinDate = joinDate;
	}

	public java.sql.Date getQuitDate() {
		return quitDate;
	}

	public void setQuitDate(java.sql.Date quitDate) {
		this.quitDate = quitDate;
	}

	public int getRegularPay() {
		return regularPay;
	}

	public void setRegularPay(int regularPay) {
		this.regularPay = regularPay;
	}

	public int getBonus() {
		return bonus;
	}

	public void setBonus(int bonus) {
		this.bonus = bonus;
	}

	public int getAnnualIncome() {
		return annualIncome;
	}

	public void setAnnualIncome(int annualIncome) {
		this.annualIncome = annualIncome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<MemberRoleDTO> getMemberRoleList() {
		return memberRoleList;
	}

	public void setMemberRoleList(List<MemberRoleDTO> memberRoleList) {
		this.memberRoleList = memberRoleList;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "UserImpl [name=" + name + ", coSerialNo=" + company.getName() + ", deptNo=" + dept.getName() + ", rankNo=" + rank.getName()
				+ ", pwd=" + pwd + ", empNo=" + empNo + ", birth=" + birth + ", phone=" + phone + ", joinDate="
				+ joinDate + ", quitDate=" + quitDate + ", regularPay=" + regularPay + ", bonus=" + bonus
				+ ", annualIncome=" + annualIncome + ", email=" + email + "]";
	}
}
