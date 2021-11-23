package com.greedy.erp_bomb.vote.model.dto;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.greedy.erp_bomb.member.model.dto.MemberDTO;

@Entity
@Table(name = "VOTE_OPTION")
@IdClass(VoteOptionPk.class)
public class VoteOptionDTO implements Serializable {
	private static final long serialVersionUID = 5710269620097686112L;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "VOTE_SERIAL_NO")
	private VoteDTO vote;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "MEMBER_NAME")
	private MemberDTO member;
	
	@Column(name = "VOTE_DESC")
	private String desc;
	
	@Column(name = "NUMBER_VOTE")
	private int voteCount;

	public VoteOptionDTO() {
	}
	public VoteOptionDTO(VoteDTO vote, MemberDTO member, String desc, int voteCount) {
		this.vote = vote;
		this.member = member;
		this.desc = desc;
		this.voteCount = voteCount;
	}
	
	public VoteDTO getVote() {
		return vote;
	}
	public void setVote(VoteDTO vote) {
		this.vote = vote;
	}
	public MemberDTO getMember() {
		return member;
	}
	public void setMember(MemberDTO member) {
		this.member = member;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getVoteCount() {
		return voteCount;
	}
	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "VoteOptionDTO [vote=" + vote + ", member=" + member + ", desc=" + desc + ", voteCount=" + voteCount
				+ "]";
	}
}
