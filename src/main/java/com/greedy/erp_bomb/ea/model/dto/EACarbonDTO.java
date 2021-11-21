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
@Table(name = "EA_CARBON")
@IdClass(EACarbonPk.class)
public class EACarbonDTO implements Serializable {
	private static final long serialVersionUID = 1925541694117506117L;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "EA_SERIAL_NO")
	private EADTO ea;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "MEMBER_NAME")
	private MemberDTO member;
	
	@Column(name = "EA_CC_REFERENCE_STATE")
	private int status;

	public EACarbonDTO() {
	}
	public EACarbonDTO(EADTO ea, MemberDTO member, int status) {
		this.ea = ea;
		this.member = member;
		this.status = status;
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
		return "EaCarbonDTO [ea=" + ea.getTitle() + ", member=" + member.getName() + ", status=" + status + "]";
	}
}
