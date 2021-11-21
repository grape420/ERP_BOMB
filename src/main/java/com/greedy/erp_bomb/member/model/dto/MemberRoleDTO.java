package com.greedy.erp_bomb.member.model.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MEMBER_ROLE")
@IdClass(MemberRolePk.class)
public class MemberRoleDTO implements Serializable {
	private static final long serialVersionUID = -7165673537069932002L;

	@Id
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="AUTHORITY_CODE")
	private AuthorityDTO authority;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "MEMBER_NAME")
	private MemberDTO member;

	public MemberRoleDTO() {
	}
	public MemberRoleDTO(AuthorityDTO authority, MemberDTO member) {
		this.authority = authority;
		this.member = member;
	}
	
	public AuthorityDTO getAuthority() {
		return authority;
	}
	public void setAuthority(AuthorityDTO authority) {
		this.authority = authority;
	}
	public MemberDTO getMember() {
		return member;
	}
	public void setMember(MemberDTO member) {
		this.member = member;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "MemberRoleDTO [authority=" + authority.getName() + ", member=" + member.getName() + "]";
	}
}