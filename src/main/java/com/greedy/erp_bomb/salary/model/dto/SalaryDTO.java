package com.greedy.erp_bomb.salary.model.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.greedy.erp_bomb.member.model.dto.MemberDTO;

@Entity
@SequenceGenerator(
		name = "SALARY_SEQ_GENERATOR",
		sequenceName = "SEQ_SALARY_CODE",
		initialValue = 1, allocationSize = 1)
@Table(name = "SALARY")
public class SalaryDTO implements Serializable {
	private static final long serialVersionUID = -9064078892790962749L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	generator = "SALARY_SEQ_GENERATOR")
	@Column(name = "SAL_SERIAL_NO")
	private int serialNo;
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_NAME")
	private MemberDTO member;
	
	@Column(name = "SAL_DATE")
	private java.util.Date Date;
	
	@Column(name = "SAL_REGULAR_PAY", nullable = true)
	private Integer regularPay;
	
	@Column(name = "SAL_BONUS", nullable = true)
	private Integer bonus;

	public SalaryDTO() {
	}

	public SalaryDTO(int serialNo, MemberDTO member, java.util.Date date, Integer regularPay, Integer bonus) {
		this.serialNo = serialNo;
		this.member = member;
		Date = date;
		this.regularPay = regularPay;
		this.bonus = bonus;
	}

	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	public MemberDTO getMember() {
		return member;
	}

	public void setMember(MemberDTO member) {
		this.member = member;
	}

	public java.util.Date getDate() {
		return Date;
	}

	public void setDate(java.util.Date date) {
		Date = date;
	}

	public Integer getRegularPay() {
		return regularPay;
	}

	public void setRegularPay(Integer regularPay) {
		this.regularPay = regularPay;
	}

	public Integer getBonus() {
		return bonus;
	}

	public void setBonus(Integer bonus) {
		this.bonus = bonus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "SalaryDTO [serialNo=" + serialNo + ", member=" + member + ", Date=" + Date + ", regularPay="
				+ regularPay + ", bonus=" + bonus + "]";
	}

}
