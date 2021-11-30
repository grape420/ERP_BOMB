package com.greedy.erp_bomb.inventory.model.dto;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(
		name = "IN_OUT_SEQ_GENERATOR",
		sequenceName = "SEQ_IN_OUT_CODE",
		initialValue = 1, allocationSize = 1)
@Table(name = "IN_OUT")
public class InOutDTO implements Serializable {
	private static final long serialVersionUID = -4152492962951566500L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	generator = "IN_OUT_SEQ_GENERATOR")
	@Column(name = "IN_OUT_NO")
	private int no;
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "CO_SERIAL_NO", referencedColumnName = "CO_SERIAL_NO"),
		@JoinColumn(name = "ICE_NO", referencedColumnName = "ICE_NO")
	})
	private InventoryDTO inventory;
	
	@Column(name = "IN_OUT_DATE")
	private java.sql.Date date;
	
	@Column(name = "IN_OUT_DIVISION")
	private int division;
	
	@Column(name = "IN_OUT_AMOUNT")
	private int amount;

	public InOutDTO() {
	}
	public InOutDTO(int no, InventoryDTO inventory, Date date, int division, int amount) {
		this.no = no;
		this.inventory = inventory;
		this.date = date;
		this.division = division;
		this.amount = amount;
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public InventoryDTO getInventory() {
		return inventory;
	}
	public void setInventory(InventoryDTO inventory) {
		this.inventory = inventory;
	}
	public java.sql.Date getDate() {
		return date;
	}
	public void setDate(java.sql.Date date) {
		this.date = date;
	}
	public int getDivision() {
		return division;
	}
	public void setDivision(int division) {
		this.division = division;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "InOutDTO [no=" + no + ", inventory=" + inventory.getInvenRemainStock() + ", date=" + date + ", division=" + division
				+ ", amount=" + amount + "]";
	}
}
