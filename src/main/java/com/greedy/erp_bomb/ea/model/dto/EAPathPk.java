package com.greedy.erp_bomb.ea.model.dto;

import java.io.Serializable;
import java.util.Objects;

public class EAPathPk implements Serializable {
	private static final long serialVersionUID = 7671654336421070324L;
	
	private int no;
	private int ea;
	
	public EAPathPk() {
	}
	public EAPathPk(int no, int ea) {
		this.no = no;
		this.ea = ea;
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getEa() {
		return ea;
	}
	public void setEa(int ea) {
		this.ea = ea;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "EAPathPk [no=" + no + ", ea=" + ea + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
		 return false;
		} else if (obj == this) {
			return true;
		} else if (obj instanceof EAPathPk) {
			EAPathPk objTemp = (EAPathPk)obj;
			if((this.ea == objTemp.getEa()) && (this.no == objTemp.getNo())) {
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
		return Objects.hash(no, ea);
	}
}
