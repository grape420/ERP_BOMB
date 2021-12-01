package com.greedy.erp_bomb.member.model.dto;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.greedy.erp_bomb.board.model.dto.BoardDTO;
import com.greedy.erp_bomb.board.model.dto.CommentDTO;
import com.greedy.erp_bomb.ea.model.dto.AddendumDTO;
import com.greedy.erp_bomb.ea.model.dto.EACarbonDTO;
import com.greedy.erp_bomb.ea.model.dto.EADTO;
import com.greedy.erp_bomb.ea.model.dto.EAPathDTO;
import com.greedy.erp_bomb.inventory.model.dto.CompanyDTO;
import com.greedy.erp_bomb.note.model.dto.NoteDTO;
import com.greedy.erp_bomb.salary.model.dto.SalaryDTO;
import com.greedy.erp_bomb.sp.model.dto.SPDTO;
import com.greedy.erp_bomb.tna.model.dto.TNADTO;
import com.greedy.erp_bomb.vote.model.dto.VoteDTO;
import com.greedy.erp_bomb.vote.model.dto.VoteOptionDTO;
import com.greedy.erp_bomb.vote.model.dto.VoteParticipationDTO;

@Entity
@Table(name = "MEMBER")
public class MemberDTO implements Serializable {
	private static final long serialVersionUID = -2851982422987143554L;

	@Id
	@Column(name = "MEMBER_NAME")
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "CO_SERIAL_NO")
	private CompanyDTO company;
	
	@ManyToOne
	@JoinColumn(name = "DEPT_NO")
	private DeptDTO dept;
	
	@ManyToOne
	@JoinColumn(name = "RANK_NO")
	private RankDTO rank;
	
	@Column(name = "MEMBER_PWD")
	private String pwd;
	
	@Column(name = "MEMBER_EMP_NO")
	private int empNo;
	
	@Column(name = "MEMBER_BIRTH")
	private String birth;
	
	@Column(name = "MEMBER_PHONE_NO")
	private String phone;
	
	@Column(name = "MEMBER_JOIN_DATE")
	private java.sql.Date joinDate;
	
	@Column(name = "MEMBER_QUIT_DATE")
	private java.sql.Date quitDate;
	
	@Column(name = "MEMBER_REGULAR_PAY")
	private int regularPay;
	
	@Column(name = "MEMBER_BONUS")
	private int bonus;
	
	@Column(name = "MEMBER_ANNUAL_INCOME")
	private int annualIncome;
	
	@Column(name = "MEMBER_EMAIL")
	private String email;
	
	@Column(name = "ENT_YN")
	private String entYn;
	
	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
	private List<MemberRoleDTO> memberRoleList = new ArrayList<>();
	
	@OneToMany(mappedBy = "member")
	private List<EADTO> eaList = new ArrayList<>();
	
	@OneToMany(mappedBy = "member")
	private List<EACarbonDTO> eaBonDTOList = new ArrayList<>();

	@OneToMany(mappedBy = "member")
	private List<AddendumDTO> addenumList = new ArrayList<>();
	
	@OneToMany(mappedBy = "member")
	private List<EAPathDTO> eaPathList = new ArrayList<>();
	
	@OneToMany(mappedBy = "member")
	private List<TNADTO> tnaList = new ArrayList<>();
	
	@OneToMany(mappedBy = "member")
	private List<VoteDTO> voteList = new ArrayList<>();
	
	@OneToMany(mappedBy = "member")
	private List<VoteParticipationDTO> voteParticipationList = new ArrayList<>();
	
	@OneToMany(mappedBy = "member")
	private List<VoteOptionDTO> voteOptionList = new ArrayList<>();
	
	@OneToMany(mappedBy = "member")
	private List<BoardDTO> boardList = new ArrayList<>();
	
	@OneToMany(mappedBy = "member")
	private List<CommentDTO> commentList = new ArrayList<>();
	
	@OneToMany(mappedBy = "sentMember")
	private List<NoteDTO> sentNoteList = new ArrayList<>();
	
	@OneToMany(mappedBy = "receiveMember")
	private List<NoteDTO> receiveMemberList = new ArrayList<>();

	@OneToMany(mappedBy = "member")
	private List<SalaryDTO> salaryList = new ArrayList<>();

	@OneToOne(mappedBy = "member")
	private SPDTO sp;
	
	public MemberDTO() {
	}

	public MemberDTO(String name, CompanyDTO company, DeptDTO dept, RankDTO rank, String pwd, int empNo, String birth,
			String phone, Date joinDate, Date quitDate, int regularPay, int bonus, int annualIncome, String email,
			String entYn, List<MemberRoleDTO> memberRoleList, List<EADTO> eaList, List<EACarbonDTO> eaBonDTOList,
			List<AddendumDTO> addenumList, List<EAPathDTO> eaPathList, List<TNADTO> tnaList, List<VoteDTO> voteList,
			List<VoteParticipationDTO> voteParticipationList, List<VoteOptionDTO> voteOptionList,
			List<BoardDTO> boardList, List<CommentDTO> commentList, List<NoteDTO> sentNoteList,
			List<NoteDTO> receiveMemberList, List<SalaryDTO> salaryList, SPDTO sp) {
		this.name = name;
		this.company = company;
		this.dept = dept;
		this.rank = rank;
		this.pwd = pwd;
		this.empNo = empNo;
		this.birth = birth;
		this.phone = phone;
		this.joinDate = joinDate;
		this.quitDate = quitDate;
		this.regularPay = regularPay;
		this.bonus = bonus;
		this.annualIncome = annualIncome;
		this.email = email;
		this.entYn = entYn;
		this.memberRoleList = memberRoleList;
		this.eaList = eaList;
		this.eaBonDTOList = eaBonDTOList;
		this.addenumList = addenumList;
		this.eaPathList = eaPathList;
		this.tnaList = tnaList;
		this.voteList = voteList;
		this.voteParticipationList = voteParticipationList;
		this.voteOptionList = voteOptionList;
		this.boardList = boardList;
		this.commentList = commentList;
		this.sentNoteList = sentNoteList;
		this.receiveMemberList = receiveMemberList;
		this.salaryList = salaryList;
		this.sp = sp;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CompanyDTO getCompany() {
		return company;
	}
	public void setCompany(CompanyDTO company) {
		this.company = company;
	}
	public DeptDTO getDept() {
		return dept;
	}
	public void setDept(DeptDTO dept) {
		this.dept = dept;
	}
	public RankDTO getRank() {
		return rank;
	}
	public void setRank(RankDTO rank) {
		this.rank = rank;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getEmpNo() {
		return empNo;
	}
	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public java.sql.Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(java.sql.Date joinDate) {
		this.joinDate = joinDate;
	}
	public java.sql.Date getQuitDate() {
		return quitDate;
	}
	public void setQuitDate(java.sql.Date quitDate) {
		this.quitDate = quitDate;
	}
	public int getRegularPay() {
		return regularPay;
	}
	public void setRegularPay(int regularPay) {
		this.regularPay = regularPay;
	}
	public int getBonus() {
		return bonus;
	}
	public void setBonus(int bonus) {
		this.bonus = bonus;
	}
	public int getAnnualIncome() {
		return annualIncome;
	}
	public void setAnnualIncome(int annualIncome) {
		this.annualIncome = annualIncome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<MemberRoleDTO> getMemberRoleList() {
		return memberRoleList;
	}
	public void setMemberRoleList(List<MemberRoleDTO> memberRoleList) {
		this.memberRoleList = memberRoleList;
	}
	public List<EADTO> getEaList() {
		return eaList;
	}
	public void setEaList(List<EADTO> eaList) {
		this.eaList = eaList;
	}
	public List<EACarbonDTO> getEaBonDTOList() {
		return eaBonDTOList;
	}
	public void setEaBonDTOList(List<EACarbonDTO> eaBonDTOList) {
		this.eaBonDTOList = eaBonDTOList;
	}
	public List<AddendumDTO> getAddenumList() {
		return addenumList;
	}
	public void setAddenumList(List<AddendumDTO> addenumList) {
		this.addenumList = addenumList;
	}
	public List<EAPathDTO> getEaPathList() {
		return eaPathList;
	}
	public void setEaPathList(List<EAPathDTO> eaPathList) {
		this.eaPathList = eaPathList;
	}
	public List<TNADTO> getTnaList() {
		return tnaList;
	}
	public void setTnaList(List<TNADTO> tnaList) {
		this.tnaList = tnaList;
	}
	public List<VoteDTO> getVoteList() {
		return voteList;
	}
	public void setVoteList(List<VoteDTO> voteList) {
		this.voteList = voteList;
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
	public List<BoardDTO> getBoardList() {
		return boardList;
	}
	public void setBoardList(List<BoardDTO> boardList) {
		this.boardList = boardList;
	}
	public List<CommentDTO> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<CommentDTO> commentList) {
		this.commentList = commentList;
	}
	public List<NoteDTO> getSentNoteList() {
		return sentNoteList;
	}
	public void setSentNoteList(List<NoteDTO> sentNoteList) {
		this.sentNoteList = sentNoteList;
	}
	public List<NoteDTO> getReceiveMemberList() {
		return receiveMemberList;
	}
	public void setReceiveMemberList(List<NoteDTO> receiveMemberList) {
		this.receiveMemberList = receiveMemberList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getEntYn() {
		return entYn;
	}
	public void setEntYn(String entYn) {
		this.entYn = entYn;
	}

	public List<SalaryDTO> getSalaryList() {
		return salaryList;
	}

	public void setSalaryList(List<SalaryDTO> salaryList) {
		this.salaryList = salaryList;
	}

	public SPDTO getSp() {
		return sp;
	}

	public void setSp(SPDTO sp) {
		this.sp = sp;
	}

	@Override
	public String toString() {
		return "MemberDTO [name=" + name + ", coSerialNo=" + company.getName() + ", deptNo=" + dept.getName() + ", rankNo=" + rank.getName()
				+ ", pwd=" + pwd + ", empNo=" + empNo + ", birth=" + birth + ", phone=" + phone + ", joinDate="
				+ joinDate + ", quitDate=" + quitDate + ", regularPay=" + regularPay + ", bonus=" + bonus
				+ ", annualIncome=" + annualIncome + ", email=" + email + ", entYn=" + entYn + "]";
	}
}
