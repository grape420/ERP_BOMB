package com.greedy.erp_bomb.inventory.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "INVENTORY")
@IdClass(InventoryPk.class)
public class InventoryDTO implements Serializable {
	private static final long serialVersionUID = 2780997980000682790L;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "ICE_NO")
	private IceCreamDTO iceCream;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "CO_SERIAL_NO")
	private CompanyDTO company;
	
	@Column(name = "INVEN_REMAIN_STOCK")
	private int invenRemainStock;
	
	@OneToMany(mappedBy = "inventory", cascade=CascadeType.ALL)
	private List<InOutDTO> inOutList = new ArrayList<>();

	public InventoryDTO() {
	}
	public InventoryDTO(IceCreamDTO iceCream, CompanyDTO company, int invenRemainStock, List<InOutDTO> inOutList) {
		this.iceCream = iceCream;
		this.company = company;
		this.invenRemainStock = invenRemainStock;
		this.inOutList = inOutList;
	}
	
	public IceCreamDTO getIceCream() {
		return iceCream;
	}
	public void setIceCream(IceCreamDTO iceCream) {
		this.iceCream = iceCream;
	}
	public CompanyDTO getCompany() {
		return company;
	}
	public void setCompany(CompanyDTO company) {
		this.company = company;
	}
	public int getInvenRemainStock() {
		return invenRemainStock;
	}
	public void setInvenRemainStock(int invenRemainStock) {
		this.invenRemainStock = invenRemainStock;
	}
	public List<InOutDTO> getInOutList() {
		return inOutList;
	}
	public void setInOutList(List<InOutDTO> inOutList) {
		this.inOutList = inOutList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "InventoryDTO [iceCream=" + iceCream.getName() + ", company=" + company.getName() + ", invenRemainStock=" + invenRemainStock
				+ "]";
	}
}
