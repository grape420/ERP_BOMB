package com.greedy.erp_bomb.ea.vo;

import java.io.Serializable;

public class ApprovalerVO implements Serializable {

	private static final long serialVersionUID = 7793682859761825708L;
	private String name;
	private int type;
	
	public ApprovalerVO() {
	}
	public ApprovalerVO(String name, int type) {
		this.name = name;
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "Approvaler [name=" + name + ", type=" + type + "]";
	}
}
