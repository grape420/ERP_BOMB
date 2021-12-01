package com.greedy.erp_bomb.note.model.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.greedy.erp_bomb.member.model.dto.MemberDTO;

@Entity
@SequenceGenerator(
		name = "NOTE_SEQ_GENERATOR",
		sequenceName = "SEQ_NOTE_CODE",
		initialValue = 1, allocationSize = 1)
@Table(name = "NOTE")
public class NoteDTO implements Serializable {
	private static final long serialVersionUID = 1488835073030041764L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	generator = "NOTE_SEQ_GENERATOR")
	@Column(name = "NOTE_SERIAL_NO")
	private int serialNo;
	
	@ManyToOne
	@JoinColumn(name = "SENT_MEMBER_NAME", referencedColumnName = "MEMBER_NAME")
	private MemberDTO sentMember;
	
	@ManyToOne
	@JoinColumn(name = "RECEIVE_MEMBER_NAME", referencedColumnName = "MEMBER_NAME")
	private MemberDTO receiveMember;
	
	@Column(name = "NOTE_SEND_DATE")
	private Date sendDate;
	
	@Column(name = "NOTE_RECEPTION")
	private String reception;
	
	@Column(name = "NOTE_CONTENT")
	private String content;

	public NoteDTO() {
	}
	public NoteDTO(int serialNo, MemberDTO sentMember, MemberDTO receiveMember, Date sendDate, String reception,
			String content) {
		this.serialNo = serialNo;
		this.sentMember = sentMember;
		this.receiveMember = receiveMember;
		this.sendDate = sendDate;
		this.reception = reception;
		this.content = content;
	}
	
	public int getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}
	public MemberDTO getSentMember() {
		return sentMember;
	}
	public void setSentMember(MemberDTO sentMember) {
		this.sentMember = sentMember;
	}
	public MemberDTO getReceiveMember() {
		return receiveMember;
	}
	public void setReceiveMember(MemberDTO receiveMember) {
		this.receiveMember = receiveMember;
	}
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	public String getReception() {
		return reception;
	}
	public void setReception(String reception) {
		this.reception = reception;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "NoteDTO [serialNo=" + serialNo + ", sentMember=" + sentMember + ", receiveMember=" + receiveMember
				+ ", sendDate=" + sendDate + ", reception=" + reception + ", content=" + content + "]";
	}
}
