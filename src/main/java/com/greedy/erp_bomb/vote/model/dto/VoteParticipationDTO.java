package com.greedy.erp_bomb.vote.model.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.greedy.erp_bomb.member.model.dto.MemberDTO;

@Entity
@Table(name = "VOTE_PARTICIPATION")
@IdClass(VoteParticipationPk.class)
public class VoteParticipationDTO implements Serializable {
	private static final long serialVersionUID = -489339299968541450L;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "VOTE_SERIAL_NO")
	private VoteDTO vote;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "MEMBER_NAME")
	private MemberDTO member;

	public VoteParticipationDTO() {
	}
	public VoteParticipationDTO(VoteDTO vote, MemberDTO member) {
		this.vote = vote;
		this.member = member;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "VoteParticipationDTO [vote=" + vote + ", member=" + member + "]";
	}
}
