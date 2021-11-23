package com.greedy.erp_bomb.sp.model.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.greedy.erp_bomb.member.model.dto.MemberDTO;

@Entity
@Table(name = "SP_MANAGEMENT")
public class SPDTO implements Serializable {
	private static final long serialVersionUID = 904924266611487226L;
	
	@Id
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
	public SPDTO(MemberDTO member, int serverancePay, int empYear) {
		this.member = member;
		this.serverancePay = serverancePay;
		this.empYear = empYear;
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
		return "SPDTO [member=" + member.getName() + ", serverancePay=" + serverancePay + ", empYear=" + empYear + "]";
	}
}
