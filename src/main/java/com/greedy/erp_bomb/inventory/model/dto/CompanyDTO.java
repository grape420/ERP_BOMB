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

import com.greedy.erp_bomb.member.model.dto.MemberDTO;

@Entity
@SequenceGenerator(
		name = "COMPANY_SEQ_GENERATOR",
		sequenceName = "SEQ_COMPANY_CODE",
		initialValue = 1, allocationSize = 1)
@Table(name = "COMPANY")
public class CompanyDTO implements Serializable {
	private static final long serialVersionUID = -8982551200851822685L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	generator = "COMPANY_SEQ_GENERATOR")
	@Column(name = "CO_SERIAL_NO")
	private int serialNo;
	
	@Column(name = "CO_DIVISION")
	private String division;
	
	@Column(name = "CO_NAME")
	private String name;
	
	@Column(name = "CO_STATUS")
	private String status;
	
	@OneToMany(mappedBy = "company")
	private List<InventoryDTO> inventoryList = new ArrayList<>();
	
	@OneToMany(mappedBy = "company")
	private List<MemberDTO> memberList = new ArrayList<>();

	public CompanyDTO() {
	}
	public CompanyDTO(int serialNo, String division, String name, String status, List<InventoryDTO> inventoryList,
			List<MemberDTO> memberList) {
		this.serialNo = serialNo;
		this.division = division;
		this.name = name;
		this.status = status;
		this.inventoryList = inventoryList;
		this.memberList = memberList;
	}
	
	public int getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<InventoryDTO> getInventoryList() {
		return inventoryList;
	}
	public void setInventoryList(List<InventoryDTO> inventoryList) {
		this.inventoryList = inventoryList;
	}
	public List<MemberDTO> getMemberList() {
		return memberList;
	}
	public void setMemberList(List<MemberDTO> memberList) {
		this.memberList = memberList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "CompanyDTO [serialNo=" + serialNo + ", division=" + division + ", name=" + name + ", status=" + status
				+ "]";
	}
}