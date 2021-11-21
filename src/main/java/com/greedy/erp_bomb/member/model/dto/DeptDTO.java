package com.greedy.erp_bomb.member.model.dto;

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
		name = "DEPT_SEQ_GENERATOR",
		sequenceName = "SEQ_DEPT_CODE",
		initialValue = 1, allocationSize = 1)
@Table(name = "DEPT")
public class DeptDTO implements Serializable {
	private static final long serialVersionUID = 686589016051486629L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	generator = "DEPT_SEQ_GENERATOR")
	@Column(name = "DEPT_NO")
	private int no;
	
	@Column(name = "DEPT_NAME")
	private String name;

	@OneToMany(mappedBy = "dept")
	private List<MemberDTO> memberList = new ArrayList<>();
	
	public DeptDTO() {
	}
	public DeptDTO(int no, String name, List<MemberDTO> memberList) {
		this.no = no;
		this.name = name;
		this.memberList = memberList;
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
		return "DeptDTO [no=" + no + ", name=" + name + "]";
	}
}
