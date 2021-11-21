package com.greedy.erp_bomb.ea.model.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.greedy.erp_bomb.member.model.dto.MemberDTO;

@Entity
@Table(name = "EA_APPROVAL_PATH")
@IdClass(EAPathPk.class)
public class EAPathDTO implements Serializable {
	private static final long serialVersionUID = -2378864764927376862L;
	
	@Id
	@Column(name = "EA_NO")
	private int no;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "EA_SERIAL_NO")
	private EADTO ea;
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_NAME")
	private MemberDTO member;
	
	@Column(name = "EA_STATUS")
	private int status;

	public EAPathDTO() {
	}
	public EAPathDTO(int no, EADTO ea, MemberDTO member, int status) {
		this.no = no;
		this.ea = ea;
		this.member = member;
		this.status = status;
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public EADTO getEa() {
		return ea;
	}
	public void setEa(EADTO ea) {
		this.ea = ea;
	}
	public MemberDTO getMember() {
		return member;
	}
	public void setMember(MemberDTO member) {
		this.member = member;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "EAPathDTO [no=" + no + ", ea=" + ea.getTitle() + ", member=" + member.getName() + ", status=" + status + "]";
	}
}
