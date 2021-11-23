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
	private java.sql.Date Date;
	
	@Column(name = "SAL_REGULAR_PAY")
	private int reularPay;
	
	@Column(name = "SAL_BONUS")
	private int bonus;

	public SalaryDTO() {
	}
	public SalaryDTO(int serialNo, MemberDTO member, java.sql.Date date, int reularPay, int bonus) {
		this.serialNo = serialNo;
		this.member = member;
		Date = date;
		this.reularPay = reularPay;
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
	public java.sql.Date getDate() {
		return Date;
	}
	public void setDate(java.sql.Date date) {
		Date = date;
	}
	public int getReularPay() {
		return reularPay;
	}
	public void setReularPay(int reularPay) {
		this.reularPay = reularPay;
	}
	public int getBonus() {
		return bonus;
	}
	public void setBonus(int bonus) {
		this.bonus = bonus;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "SalaryDTO [serialNo=" + serialNo + ", member=" + member + ", Date=" + Date + ", reularPay=" + reularPay
				+ ", bonus=" + bonus + "]";
	}
}
