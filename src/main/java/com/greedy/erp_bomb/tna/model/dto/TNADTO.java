package com.greedy.erp_bomb.tna.model.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.greedy.erp_bomb.member.model.dto.MemberDTO;

@Entity
@Table(name = "TNA")
@IdClass(TNAPk.class)
public class TNADTO implements Serializable {
	private static final long serialVersionUID = 6388375945447424800L;
	
	@Id
	@Column(name = "TNA_DATE")
	private String date;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "MEMBER_NAME")
	private MemberDTO member;
	
	@Column(name = "TNA_CODE")
	private int code;
	
	@Transient
	private java.util.Date utilDate;

	public TNADTO() {
	}
	public TNADTO(String date, MemberDTO member, int code, java.util.Date utilDate) {
		this.date = date;
		this.member = member;
		this.code = code;
		this.utilDate = utilDate;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public MemberDTO getMember() {
		return member;
	}
	public void setMember(MemberDTO member) {
		this.member = member;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public java.util.Date getUtilDate() {
		return utilDate;
	}
	public void setUtilDate(java.util.Date utilDate) {
		this.utilDate = utilDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "TNADTO [date=" + date + ", member=" + member.getName() + ", code=" + code + ", utilDate=" + utilDate + "]";
	}
}
