package com.greedy.erp_bomb.member.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "GLOBAL_MENU")
public class GlobalMenuDTO implements Serializable {
	private static final long serialVersionUID = 7872793544769339571L;

	@Id
	@Column(name = "MENU_CODE")
	private int code;				// 메뉴코드
	
	@Column(name = "MENU_NAME")
	private String name;			// 메뉴명
	
	@Column(name = "MENU_URL")
	private String url;				// 메뉴URL
	
	@Column(name = "MENU_DESC")
	private String desc;			// 메뉴설명
	
	@Column(name = "MENU_ORDER")
	private int order;				// 출력순서
	
	@Column(name = "REMOVED_YN")
	private String removedYn;		// 삭제여부
	
	@Column(name = "MENU_TYPE")
	private int type;				// 구분
	
	@ManyToOne
	@JoinColumn(name = "REF_MENU_CODE")
	private GlobalMenuDTO globalMenu;	// 상위메뉴코드
	
	@OneToMany(mappedBy = "globalMenu")
	private List<GlobalMenuDTO> globalMenuList = new ArrayList<>();
	
	@OneToMany(mappedBy = "globalMenu")
	private List<AuthenticatedMenuDTO> authenticatedMenu = new ArrayList<>();

	public GlobalMenuDTO() {
	}
	public GlobalMenuDTO(int code, String name, String url, String desc, int order, String removedYn, int type,
			GlobalMenuDTO globalMenu, List<GlobalMenuDTO> globalMenuList,
			List<AuthenticatedMenuDTO> authenticatedMenu) {
		this.code = code;
		this.name = name;
		this.url = url;
		this.desc = desc;
		this.order = order;
		this.removedYn = removedYn;
		this.type = type;
		this.globalMenu = globalMenu;
		this.globalMenuList = globalMenuList;
		this.authenticatedMenu = authenticatedMenu;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public String getRemovedYn() {
		return removedYn;
	}
	public void setRemovedYn(String removedYn) {
		this.removedYn = removedYn;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public GlobalMenuDTO getGlobalMenu() {
		return globalMenu;
	}
	public void setGlobalMenu(GlobalMenuDTO globalMenu) {
		this.globalMenu = globalMenu;
	}
	public List<GlobalMenuDTO> getGlobalMenuList() {
		return globalMenuList;
	}
	public void setGlobalMenuList(List<GlobalMenuDTO> globalMenuList) {
		this.globalMenuList = globalMenuList;
	}
	public List<AuthenticatedMenuDTO> getAuthenticatedMenu() {
		return authenticatedMenu;
	}
	public void setAuthenticatedMenu(List<AuthenticatedMenuDTO> authenticatedMenu) {
		this.authenticatedMenu = authenticatedMenu;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "GlobalMenuDTO [code=" + code + ", name=" + name + ", url=" + url + ", desc=" + desc + ", order=" + order
				+ ", removedYn=" + removedYn + ", type=" + type + ", globalMenu=" + globalMenu.getName() + "]";
	}
}
