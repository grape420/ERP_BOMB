package com.greedy.erp_bomb.salary.model.dto;

import java.io.Serializable;
import java.sql.Date;

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
@Table(name = "SALARY")
@SequenceGenerator(
		name = "SALARY_SEQ_GENERATOR",
		sequenceName = "SEQ_SALARY_CODE",
		initialValue = 1, allocationSize = 1)
public class SalaryDTO implements Serializable{
	private static final long serialVersionUID = 6716142428275347252L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	generator = "SALARY_SEQ_GENERATOR")
	@Column(name = "SEQ_SALARY_CODE")
	private int salSerialNo;	
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_NAME")
	private MemberDTO member;	
	
	@Column(name = "SAL_DATE")
	private java.sql.Date salDate;
	
	@Column(name = "SAL_REGULAR_PAY")
	private int salReularPay;
	
	@Column(name = "SAL_BONUS")
	private int salBonus;

	public SalaryDTO() {
	}

	public SalaryDTO(int salSerialNo, MemberDTO member, Date salDate, int salReularPay, int salBonus) {
		this.salSerialNo = salSerialNo;
		this.member = member;
		this.salDate = salDate;
		this.salReularPay = salReularPay;
		this.salBonus = salBonus;
	}

	public int getSalSerialNo() {
		return salSerialNo;
	}

	public void setSalSerialNo(int salSerialNo) {
		this.salSerialNo = salSerialNo;
	}

	public MemberDTO getMember() {
		return member;
	}

	public void setMember(MemberDTO member) {
		this.member = member;
	}

	public java.sql.Date getSalDate() {
		return salDate;
	}

	public void setSalDate(java.sql.Date salDate) {
		this.salDate = salDate;
	}

	public int getSalReularPay() {
		return salReularPay;
	}

	public void setSalReularPay(int salReularPay) {
		this.salReularPay = salReularPay;
	}

	public int getSalBonus() {
		return salBonus;
	}

	public void setSalBonus(int salBonus) {
		this.salBonus = salBonus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "SalaryDTO [salSerialNo=" + salSerialNo + ", member=" + member + ", salDate=" + salDate
				+ ", salReularPay=" + salReularPay + ", salBonus=" + salBonus + "]";
	}
	
	
	
}
