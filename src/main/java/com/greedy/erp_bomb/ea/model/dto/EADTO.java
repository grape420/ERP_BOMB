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
		name = "EA_SEQ_GENERATOR",
		sequenceName = "SEQ_EA_CODE",
		initialValue = 1, allocationSize = 1)
@Table(name = "ELECTRONIC_APPROVAL")
public class EADTO implements Serializable {
	private static final long serialVersionUID = -2118009690203268689L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	generator = "EA_SEQ_GENERATOR")
	@Column(name = "EA_SERIAL_NO")
	private int serialNo;
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_NAME")
	private MemberDTO member;
	
	@Column(name = "EA_DATE")
	private java.sql.Date date;
	
	@Column(name = "EA_TITLE")
	private String title;
	
	@Column(name = "EA_CONTENT")
	private String content;
	
	@Column(name = "EA_CATEGORY")
	private int category;
	
	@Column(name = "EA_SAVENUM")
	private int saveNo;
	
	@OneToMany(mappedBy = "ea")
	private List<AddendumDTO> addendumList = new ArrayList<>();
	
	@OneToMany(mappedBy = "ea")
	private List<EACarbonDTO> eaCarbonList = new ArrayList<>();
	
	@OneToMany(mappedBy = "ea")
	private List<EAPathDTO> eaApprovalPathList = new ArrayList<>();

	public EADTO() {
	}
	public EADTO(int serialNo, MemberDTO member, Date date, String title, String content, int category, int saveNo,
			List<AddendumDTO> addendumList, List<EACarbonDTO> eaCarbonList, List<EAPathDTO> eaApprovalPathList) {
		this.serialNo = serialNo;
		this.member = member;
		this.date = date;
		this.title = title;
		this.content = content;
		this.category = category;
		this.saveNo = saveNo;
		this.addendumList = addendumList;
		this.eaCarbonList = eaCarbonList;
		this.eaApprovalPathList = eaApprovalPathList;
	}
	
	public int getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}
	public MemberDTO getMember() {
		return member;
	}
	public void setMember(MemberDTO member) {
		this.member = member;
	}
	public java.sql.Date getDate() {
		return date;
	}
	public void setDate(java.sql.Date date) {
		this.date = date;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public int getSaveNo() {
		return saveNo;
	}
	public void setSaveNo(int saveNo) {
		this.saveNo = saveNo;
	}
	public List<AddendumDTO> getAddendumList() {
		return addendumList;
	}
	public void setAddendumList(List<AddendumDTO> addendumList) {
		this.addendumList = addendumList;
	}
	public List<EACarbonDTO> getEaCarbonList() {
		return eaCarbonList;
	}
	public void setEaCarbonList(List<EACarbonDTO> eaCarbonList) {
		this.eaCarbonList = eaCarbonList;
	}
	public List<EAPathDTO> getEaApprovalPathList() {
		return eaApprovalPathList;
	}
	public void setEaApprovalPathList(List<EAPathDTO> eaApprovalPathList) {
		this.eaApprovalPathList = eaApprovalPathList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "EADTO [serialNo=" + serialNo + ", member=" + member.getName() + ", date=" + date + ", title=" + title
				+ ", content=" + content + ", category=" + category + ", saveNo=" + saveNo + "]";
	}
}
