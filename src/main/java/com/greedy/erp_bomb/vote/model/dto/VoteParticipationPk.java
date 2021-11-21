package com.greedy.erp_bomb.vote.model.dto;

import java.io.Serializable;
import java.util.Objects;

public class VoteParticipationPk implements Serializable {
	private static final long serialVersionUID = -6322007311478137121L;
	
	private int vote;
	private String member;
	
	public VoteParticipationPk() {
	}
	public VoteParticipationPk(int vote, String member) {
		this.vote = vote;
		this.member = member;
	}
	
	public int getVote() {
		return vote;
	}
	public void setVote(int vote) {
		this.vote = vote;
	}
	public String getMember() {
		return member;
	}
	public void setMember(String member) {
		this.member = member;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "VoteParticipationPk [vote=" + vote + ", member=" + member + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
		 return false;
		} else if (obj == this) {
			return true;
		} else if (obj instanceof VoteParticipationPk) {
			VoteParticipationPk objTemp = (VoteParticipationPk)obj;
			if(this.vote == objTemp.getVote()) {
				if(member == null) {
					if(objTemp.getMember() == null) {
						return true;
					} else {
						return false;
					}
				} else {
					if(this.member.equals(objTemp.getMember())) {
						return true;
					} else {
						return false;
					}
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(vote, member);
	}
}
