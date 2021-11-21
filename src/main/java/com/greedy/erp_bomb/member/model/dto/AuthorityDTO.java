package com.greedy.erp_bomb.member.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "AUTHORITY")
public class AuthorityDTO implements Serializable {
	private static final long serialVersionUID = -6161628586865281467L;

	@Id
	@Column(name = "AUTHORITY_CODE")
	private int code;		// 권한코드(PK)
	
	@Column(name = "AUTHORITY_NAME")
	private String name;	// 권한명
	
	@Column(name = "AUTHORITY_DESC")
	private String desc;	// 권한설명
	
	@OneToMany(mappedBy = "authority", fetch = FetchType.EAGER)
	private List<AuthenticatedMenuDTO> authenticatedMenuList = new ArrayList<>();
	
	@OneToMany(mappedBy = "authority")
	private List<MemberRoleDTO> memberRoleList = new ArrayList<>();

	public AuthorityDTO() {
	}
	public AuthorityDTO(int code, String name, String desc, List<AuthenticatedMenuDTO> authenticatedMenuList,
			List<MemberRoleDTO> memberRoleList) {
		this.code = code;
		this.name = name;
		this.desc = desc;
		this.authenticatedMenuList = authenticatedMenuList;
		this.memberRoleList = memberRoleList;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public List<AuthenticatedMenuDTO> getAuthenticatedMenuList() {
		return authenticatedMenuList;
	}
	public void setAuthenticatedMenuList(List<AuthenticatedMenuDTO> authenticatedMenuList) {
		this.authenticatedMenuList = authenticatedMenuList;
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
		return "AuthorityDTO [code=" + code + ", name=" + name + ", desc=" + desc + "]";
	}
}
