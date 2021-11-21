package com.greedy.erp_bomb.member.model.dto;

import java.io.Serializable;
import java.util.Objects;

public class AuthenticatedMenuPk implements Serializable {
	
	private static final long serialVersionUID = -8906061299648707320L;
	private int authority;
	private int globalMenu;
	
	public AuthenticatedMenuPk() {
	}
	public AuthenticatedMenuPk(int authority, int globalMenu) {
		this.authority = authority;
		this.globalMenu = globalMenu;
	}

	public int getAuthorityCode() {
		return authority;
	}
	public void setAuthorityCode(int authority) {
		this.authority = authority;
	}
	public int getMenuCode() {
		return globalMenu;
	}
	public void setMenuCode(int globalMenu) {
		this.globalMenu = globalMenu;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "AuthenticatedMenuPk [authorityCode=" + authority + ", menuCode=" + globalMenu + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
		 return false;
		} else if (obj == this) {
			return true;
		} else if (obj instanceof AuthenticatedMenuPk) {
			AuthenticatedMenuPk objTemp = (AuthenticatedMenuPk)obj;
			if((this.authority == objTemp.getAuthorityCode()) && (this.globalMenu == objTemp.getMenuCode())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(authority, globalMenu);
	}
}
