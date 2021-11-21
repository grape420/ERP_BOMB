package com.greedy.erp_bomb.ea.model.dto;

import java.io.Serializable;
import java.util.Objects;

public class EACarbonPk implements Serializable {
	private static final long serialVersionUID = 419839748632715041L;
	private int ea;
	private String member;
	
	public EACarbonPk() {
	}
	public EACarbonPk(int ea, String member) {
		this.ea = ea;
		this.member = member;
	}
	
	public int getEa() {
		return ea;
	}
	public void setEa(int ea) {
		this.ea = ea;
	}
	public String getMember() {
		return member;
	}
	public void setMember(String member) {
		this.member = member;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "EaCarbonPk [ea=" + ea + ", member=" + member + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
		 return false;
		} else if (obj == this) {
			return true;
		} else if (obj instanceof EACarbonPk) {
			EACarbonPk objTemp = (EACarbonPk)obj;
			if(this.ea == objTemp.getEa()){
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
		return Objects.hash(ea, member);
	}
}
