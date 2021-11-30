package com.greedy.erp_bomb.tna.model.dto;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class TNAPk implements Serializable {
	private static final long serialVersionUID = -5114884986733995330L;
	
	private String date;
	private String member;
	
	public TNAPk() {
	}
	public TNAPk(String date, String member) {
		this.date = date;
		this.member = member;
	}

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
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
		return "TNAPk [date=" + date + ", member=" + member + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
		 return false;
		} else if (obj == this) {
			return true;
		} else if (obj instanceof TNAPk) {
			TNAPk objTemp = (TNAPk)obj;
			if(this.member == null) {
				if(objTemp.getMember() == null) {
					if(this.date == null) {
						if(objTemp.getDate() == null) {
							return true;
						} else {
							return false;
						}
					} else {
						if(objTemp.getDate() == null) {
							return false;
						} else {
							if(this.date.equals(objTemp.getDate())) {
								return true;
							} else {
								return false;
							}
						}
					}
				} else {
					return false;
				}
			} else {
				if(objTemp.getMember() == null) {
					return false;
				} else {
					if(this.member.equals(objTemp.getMember())) {
						if(this.date == null) {
							if(objTemp.getDate() == null) {
								return true;
							} else {
								return false;
							}
						} else {
							if(objTemp.getDate() == null) {
								return false;
							} else {
								if(this.date.equals(objTemp.getDate())) {
									return true;
								} else {
									return false;
								}
							}
						}
					} else {
						return false;
					}
				}
			}
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(date, member);
	}
}
