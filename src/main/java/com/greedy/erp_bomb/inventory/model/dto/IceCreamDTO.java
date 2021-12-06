package com.greedy.erp_bomb.inventory.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(
		name = "ICECREAM_SEQ_GENERATOR",
		sequenceName = "SEQ_ICECREAM_CODE",
		initialValue = 1, allocationSize = 1)
@Table(name = "ICECREAM")
public class IceCreamDTO implements Serializable {
	private static final long serialVersionUID = 1304456855576670336L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	generator = "ICECREAM_SEQ_GENERATOR")
	@Column(name = "ICE_NO")
	private int no;
	
	@Column(name = "ICE_NAME")
	private String name;
	
	@OneToMany(mappedBy = "iceCream")
	private List<InventoryDTO> inventoryList = new ArrayList<>();

	public IceCreamDTO() {
	}
	public IceCreamDTO(int no, String name, List<InventoryDTO> inventoryList) {
		this.no = no;
		this.name = name;
		this.inventoryList = inventoryList;
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<InventoryDTO> getInventoryList() {
		return inventoryList;
	}
	public void setInventoryList(List<InventoryDTO> inventoryList) {
		this.inventoryList = inventoryList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "IceCreamDTO [no=" + no + ", name=" + name + "]";
	}
}