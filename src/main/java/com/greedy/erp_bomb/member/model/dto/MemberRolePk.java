package com.greedy.erp_bomb.member.model.dto;

import java.io.Serializable;
import java.util.Objects;

public class MemberRolePk implements Serializable {

	private static final long serialVersionUID = -2590558293148355971L;
	private String member;					// 회원번호
	private int authority;				// 권한코드
	
	public MemberRolePk() {
	}
	public MemberRolePk(String member, int authority) {
		this.member = member;
		this.authority = authority;
	}
	
	public String getMember() {
		return member;
	}
	public void setMember(String member) {
		this.member = member;
	}
	public int getAuthority() {
		return authority;
	}
	public void setAuthority(int authority) {
		this.authority = authority;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "MemberRolePk [member=" + member + ", authority=" + authority + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
		 return false;
		} else if (obj == this) {
			return true;
		} else if (obj instanceof MemberRolePk) {
			MemberRolePk objTemp = (MemberRolePk)obj;
			if(this.authority == objTemp.getAuthority()){
				if(member == null) {
					if(objTemp.getMember() == null) {
						return true;
					} else {
						return false;
					}
				} else {
					if(this.member.equals(objTemp.getMember())) {
						return true;
					} else {
						return false;
					}
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(authority, member);
	}
}
