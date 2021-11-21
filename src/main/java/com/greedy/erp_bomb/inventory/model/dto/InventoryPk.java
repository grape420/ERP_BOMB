package com.greedy.erp_bomb.inventory.model.dto;

import java.io.Serializable;
import java.util.Objects;

public class InventoryPk implements Serializable {
	private static final long serialVersionUID = -2049069235588581731L;
	
	private int iceCream;
	private int company;
	
	public InventoryPk() {
	}
	public InventoryPk(int iceCream, int company) {
		this.iceCream = iceCream;
		this.company = company;
	}
	
	public int getIceCream() {
		return iceCream;
	}
	public void setIceCream(int iceCream) {
		this.iceCream = iceCream;
	}
	public int getCompany() {
		return company;
	}
	public void setCompany(int company) {
		this.company = company;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "InventoryPk [iceCream=" + iceCream + ", company=" + company + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
		 return false;
		} else if (obj == this) {
			return true;
		} else if (obj instanceof InventoryPk) {
			InventoryPk objTemp = (InventoryPk)obj;
			if((this.iceCream == objTemp.getIceCream()) && (this.company == objTemp.getCompany())) {
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
		return Objects.hash(iceCream, company);
	}
}
