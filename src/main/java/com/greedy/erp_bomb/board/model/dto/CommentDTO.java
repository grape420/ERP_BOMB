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
		name = "COMMENT_SEQ_GENERATOR",
		sequenceName = "SEQ_COMMENT_CODE",
		initialValue = 1, allocationSize = 1)
@Table(name = "COMMENT")
public class CommentDTO implements Serializable {
	private static final long serialVersionUID = 2552178329837419126L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	generator = "COMMENT_SEQ_GENERATOR")
	@Column(name = "COMMENT_NO")
	private int no;
	
	@ManyToOne
	@JoinColumn(name = "REF_COMMENT_NO")
	private CommentDTO refNo;
	
	@ManyToOne
	@JoinColumn(name = "BOARD_NO")
	private BoardDTO board;
	
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
	
	@Column(name = "COMMENT_STATUS")
	private String status;
	
	@OneToMany(mappedBy = "refNo")
	private List<CommentDTO> commentList = new ArrayList<>();

	public CommentDTO() {
	}
	public CommentDTO(int no, CommentDTO refNo, BoardDTO board, MemberDTO member, String content, Date date, int depth,
			int length, String status) {
		this.no = no;
		this.refNo = refNo;
		this.board = board;
		this.member = member;
		this.content = content;
		this.date = date;
		this.depth = depth;
		this.length = length;
		this.status = status;
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public CommentDTO getRefNo() {
		return refNo;
	}
	public void setRefNo(CommentDTO refNo) {
		this.refNo = refNo;
	}
	public BoardDTO getBoard() {
		return board;
	}
	public void setBoard(BoardDTO board) {
		this.board = board;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "CommentDTO [no=" + no + ", refNo=" + refNo.getNo() + ", board=" + board.getTitle() + ", member=" + member.getName() + ", content="
				+ content + ", date=" + date + ", depth=" + depth + ", length=" + length + ", status=" + status + "]";
	}
}
