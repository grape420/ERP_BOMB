package com.greedy.erp_bomb.board.model.dto;

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
		name = "BOARD_SEQ_GENERATOR",
		sequenceName = "SEQ_BOARD_CODE",
		initialValue = 1, allocationSize = 1)
@Table(name = "BOARD")
public class BoardDTO implements Serializable {
	private static final long serialVersionUID = -3526577256043753754L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	generator = "BOARD_SEQ_GENERATOR")
	@Column(name = "BOARD_NO")
	private int no;
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_NAME")
	private MemberDTO member;
	
	@Column(name = "BOARD_CONTENT")
	private String content;
	
	@Column(name = "REPORTING_DATE")
	private java.sql.Date regDate;
	
	@Column(name = "BOARD_TITLE")
	private String title;
	
	@Column(name = "BOARD_HITS")
	private int hit;
	
	@Column(name = "BOARD_CATEGORY")
	private int category;
	
	@OneToMany(mappedBy = "board")
	private List<CommentDTO> commentList = new ArrayList<>();

	public BoardDTO() {
	}
	public BoardDTO(int no, MemberDTO member, String content, Date regDate, String title, int hit, int category,
			List<CommentDTO> commentList) {
		this.no = no;
		this.member = member;
		this.content = content;
		this.regDate = regDate;
		this.title = title;
		this.hit = hit;
		this.category = category;
		this.commentList = commentList;
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
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
	public java.sql.Date getRegDate() {
		return regDate;
	}
	public void setRegDate(java.sql.Date regDate) {
		this.regDate = regDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public List<CommentDTO> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<CommentDTO> commentList) {
		this.commentList = commentList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "BoardDTO [no=" + no + ", member=" + member + ", content=" + content + ", regDate=" + regDate
				+ ", title=" + title + ", hit=" + hit + ", category=" + category + "]";
	}
}
