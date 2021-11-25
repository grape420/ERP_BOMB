package com.greedy.erp_bomb.ea.model.dto;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.greedy.erp_bomb.member.model.dto.MemberDTO;

@Entity
@SequenceGenerator(
		name = "ADDENDUM_SEQ_GENERATOR",
		sequenceName = "SEQ_ADDENDUM_CODE",
		initialValue = 1, allocationSize = 1)
@Table(name ="ADDENDUM")
public class AddendumDTO implements Serializable,Comparable<AddendumDTO> {
	private static final long serialVersionUID = -2785740755032023520L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	generator = "ADDENDUM_SEQ_GENERATOR")
	@Column(name = "COMMENT_NO")
	private Integer no;
	
	@ManyToOne
	@JoinColumn(name = "REF_COMMENT_NO")
	private AddendumDTO refNo;
	
	@ManyToOne
	@JoinColumn(name = "EA_SERIAL_NO")
	private EADTO ea;
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_NAME")
	private MemberDTO member;
	
	@Column(name = "COMMENT_CONTENT")
	private String content;
	
	@Column(name = "COMMENT_DATE")
	private java.sql.Date date;
	
	@Column(name = "COMMENT_DEPTH")
	private int depth;
	
	@Column(name = "COMMENT_LENGTH")
	private int length;
	
	@Column(name = "COMMENT_YN")
	private String requestYn;
	
	@OneToMany(mappedBy = "refNo")
	private List<AddendumDTO> addendumList = new ArrayList<>();

	public AddendumDTO() {
	}
	public AddendumDTO(Integer no, AddendumDTO refNo, EADTO ea, MemberDTO member, String content, Date date, int depth,
			int length, String requestYn, List<AddendumDTO> addendumList) {
		this.no = no;
		this.refNo = refNo;
		this.ea = ea;
		this.member = member;
		this.content = content;
		this.date = date;
		this.depth = depth;
		this.length = length;
		this.requestYn = requestYn;
		this.addendumList = addendumList;
	}
	public Integer getNo() {
		return no;
	}
	public void setNo(Integer no) {
		this.no = no;
	}
	public AddendumDTO getRefNo() {
		return refNo;
	}
	public void setRefNo(AddendumDTO refNo) {
		this.refNo = refNo;
	}
	public EADTO getEa() {
		return ea;
	}
	public void setEa(EADTO ea) {
		this.ea = ea;
	}
	public MemberDTO getMember() {
		return member;
	}
	public void setMember(MemberDTO member) {
		this.member = member;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public java.sql.Date getDate() {
		return date;
	}
	public void setDate(java.sql.Date date) {
		this.date = date;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public String getRequestYn() {
		return requestYn;
	}
	public void setRequestYn(String requestYn) {
		this.requestYn = requestYn;
	}
	public List<AddendumDTO> getAddendumList() {
		return addendumList;
	}
	public void setAddendumList(List<AddendumDTO> addendumList) {
		this.addendumList = addendumList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "AddendumDTO [no=" + no + ", refNo=" + refNo + ", ea=" + ea.getTitle() + ", member=" + member.getName() + ", content="
				+ content + ", date=" + date + ", dept=" + depth + ", length=" + length + ", requestYn=" + requestYn + "]";
	}
	@Override
	public int compareTo(AddendumDTO o) {
		if (o.getLength() < this.length) {
			return 1;
		} else  {
			return -1;
		}
	}
}
