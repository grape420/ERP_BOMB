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
@Table(name = "AUTHENTICATED_MENU")
@IdClass(AuthenticatedMenuPk.class)
public class AuthenticatedMenuDTO implements Serializable {
	private static final long serialVersionUID = -1718280174834111628L;

	@Id
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="MENU_CODE")
	private GlobalMenuDTO globalMenu;
	
	@Id
	@ManyToOne
	@JoinColumn(name="AUTHORITY_CODE")
	private AuthorityDTO authority;

	public AuthenticatedMenuDTO() {
	}
	public AuthenticatedMenuDTO(GlobalMenuDTO globalMenu, AuthorityDTO authority) {
		this.globalMenu = globalMenu;
		this.authority = authority;
	}
	
	public GlobalMenuDTO getGlobalMenu() {
		return globalMenu;
	}
	public void setGlobalMenu(GlobalMenuDTO globalMenu) {
		this.globalMenu = globalMenu;
	}
	public AuthorityDTO getAuthority() {
		return authority;
	}
	public void setAuthority(AuthorityDTO authority) {
		this.authority = authority;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "AuthenticatedMenuDTO [globalMenu=" + globalMenu.getName() + ", authority=" + authority.getName() + "]";
	}
}
