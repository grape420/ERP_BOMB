package com.greedy.erp_bomb.vote.model.dto;

import java.io.Serializable;
import java.util.Objects;

public class VoteOptionPk implements Serializable {
	private static final long serialVersionUID = 5309831966032992969L;
	
	private int vote;
	private String member;
	
	public VoteOptionPk() {
	}
	public VoteOptionPk(int vote, String member) {
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
		return "VoteOptionPk [vote=" + vote + ", member=" + member + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
		 return false;
		} else if (obj == this) {
			return true;
		} else if (obj instanceof VoteOptionPk) {
			VoteOptionPk objTemp = (VoteOptionPk)obj;
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
