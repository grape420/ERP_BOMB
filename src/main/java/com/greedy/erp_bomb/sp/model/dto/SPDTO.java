package com.greedy.erp_bomb.sp.model.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.greedy.erp_bomb.member.model.dto.MemberDTO;

@Entity
@SequenceGenerator(
		name = "SP_SEQ_GENERATOR",
		sequenceName = "SEQ_SP_CODE",
		initialValue = 1, allocationSize = 1)
@Table(name = "SP_MANAGEMENT")
public class SPDTO implements Serializable {
	private static final long serialVersionUID = 904924266611487226L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	generator = "SP_SEQ_GENERATOR")
	@Column(name = "SP_NO")
	private int spNo;
	
	@OneToOne
	@JoinColumn(name = "MEMBER_NAME")
	private MemberDTO member;
	
	@Column(name = "SERVERANCE_PAY")
	private int serverancePay;
	
	@Column(name = "EMP_YEAR")
	private int empYear;

	public SPDTO() {
	}
	public SPDTO(int spNo, MemberDTO member, int serverancePay, int empYear) {
		this.spNo = spNo;
		this.member = member;
		this.serverancePay = serverancePay;
		this.empYear = empYear;
	}
	
	public int getSpNo() {
		return spNo;
	}
	public void setSpNo(int spNo) {
		this.spNo = spNo;
	}
	public MemberDTO getMember() {
		return member;
	}
	public void setMember(MemberDTO member) {
		this.member = member;
	}
	public int getServerancePay() {
		return serverancePay;
	}
	public void setServerancePay(int serverancePay) {
		this.serverancePay = serverancePay;
	}
	public int getEmpYear() {
		return empYear;
	}
	public void setEmpYear(int empYear) {
		this.empYear = empYear;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "SPDTO [spNo=" + spNo + ", member=" + member.getName() + ", serverancePay=" + serverancePay + ", empYear="
				+ empYear + "]";
	}
}
