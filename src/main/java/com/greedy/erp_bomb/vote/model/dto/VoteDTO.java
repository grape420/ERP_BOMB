package com.greedy.erp_bomb.vote.model.dto;

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
		name = "VOTE_SEQ_GENERATOR",
		sequenceName = "SEQ_VOTE_CODE",
		initialValue = 1, allocationSize = 1)
@Table(name = "VOTE")
public class VoteDTO implements Serializable {
	private static final long serialVersionUID = 5628466697065476221L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	generator = "VOTE_SEQ_GENERATOR")
	@Column(name = "VOTE_SERIAL_NO")
	private int serialNo;
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_NAME")
	private MemberDTO member;
	
	@Column(name = "VOTE_TITLE")
	private String title;
	
	@Column(name = "VOTE_CONTENT")
	private String content;
	
	@Column(name = "VOTE_CATEGORY_NO")
	private int categoryNo;
	
	@Column(name = "VOTE_REG_DATE")
	private java.sql.Date regDate;
	
	@Column(name = "VOTE_END_DATE")
	private java.sql.Date endDate;
	
	@OneToMany(mappedBy = "vote")
	private List<VoteParticipationDTO> voteParticipationList = new ArrayList<>();
	
	@OneToMany(mappedBy = "vote")
	private List<VoteOptionDTO> voteOptionList = new ArrayList<>();

	public VoteDTO() {
	}
	public VoteDTO(int serialNo, MemberDTO member, String title, String content, int categoryNo, Date regDate,
			Date endDate, List<VoteParticipationDTO> voteParticipationList, List<VoteOptionDTO> voteOptionList) {
		this.serialNo = serialNo;
		this.member = member;
		this.title = title;
		this.content = content;
		this.categoryNo = categoryNo;
		this.regDate = regDate;
		this.endDate = endDate;
		this.voteParticipationList = voteParticipationList;
		this.voteOptionList = voteOptionList;
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
	public int getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}
	public java.sql.Date getRegDate() {
		return regDate;
	}
	public void setRegDate(java.sql.Date regDate) {
		this.regDate = regDate;
	}
	public java.sql.Date getEndDate() {
		return endDate;
	}
	public void setEndDate(java.sql.Date endDate) {
		this.endDate = endDate;
	}
	public List<VoteParticipationDTO> getVoteParticipationList() {
		return voteParticipationList;
	}
	public void setVoteParticipationList(List<VoteParticipationDTO> voteParticipationList) {
		this.voteParticipationList = voteParticipationList;
	}
	public List<VoteOptionDTO> getVoteOptionList() {
		return voteOptionList;
	}
	public void setVoteOptionList(List<VoteOptionDTO> voteOptionList) {
		this.voteOptionList = voteOptionList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "VoteDTO [serialNo=" + serialNo + ", member=" + member.getName() + ", title=" + title + ", content=" + content
				+ ", categoryNo=" + categoryNo + ", regDate=" + regDate + ", endDate=" + endDate + "]";
	}
}
