package com.greedy.erp_bomb.ea.model.dto;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
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
import javax.persistence.Transient;

import com.greedy.erp_bomb.ea.vo.ApprovalerVO;
import com.greedy.erp_bomb.member.model.dto.MemberDTO;

@Entity
@SequenceGenerator(
		name = "EA_SEQ_GENERATOR",
		sequenceName = "SEQ_EA_CODE",
		initialValue = 1, allocationSize = 1)
@Table(name = "ELECTRONIC_APPROVAL")
public class EADTO implements Serializable, Comparable<EADTO> {
	private static final long serialVersionUID = -2118009690203268689L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EA_SEQ_GENERATOR")
	@Column(name = "EA_SERIAL_NO")
	private int serialNo;
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_NAME")
	private MemberDTO member;
	
	@Column(name = "EA_DATE")
	private java.sql.Date date;
	
	@Column(name = "EA_TITLE")
	private String title;
	
	@Column(name = "EA_CONTENT")
	private String content;
	
	/*
	 * 결재 상태를 보여주는 컬럼
	 * 1 : 결재 중
	 * 2 : 결재 완료
	 * 3 : 결재 반려
	 * 4 : 임시 저장
	 */
	@Column(name = "EA_STATUS")
	private int eaStatus;
	
	/*
	 * 현재 사용자가 결재 문서에 사용할 기능을 구분하는 속성
	 * 같은 결재 문서여도 사용자마다 기능이 구분되어있어 DB 컬럼에서는 제외 함
	 * 결재 라인과 결재 상태에 따라서 Controller에서 속성의 값을 결정 함
	 * 1 : 결재, 반려(접속자가 결재 순번)
	 * 2 : 결재 취소(접속자가 결재를 하고 다음 결재자가 결재/반려를 하지 않음)
	 * 3 : 결재 취소(접속자가 기안자이면서 처음 결재자가 결재/반려를 하지 않음)
	 * 4 : 없음(자신이 결재/반려/결재 취소를 하는 결재 순번이 아니거나 참조자)
	 */
	@Transient
	private int saveNo;
	
	@OneToMany(mappedBy = "ea", cascade = CascadeType.ALL)
	private List<AddendumDTO> addendumList = new ArrayList<>();
	
	@OneToMany(mappedBy = "ea", cascade = CascadeType.ALL)
	private List<EACarbonDTO> eaCarbonList = new ArrayList<>();
	
	@OneToMany(mappedBy = "ea", cascade = CascadeType.ALL)
	private List<EAPathDTO> eaApprovalPathList = new ArrayList<>();

	@Transient
	private List<ApprovalerVO> approvalerList = new ArrayList<>();
	
	@Transient
	private List<ApprovalerVO> carbonerList = new ArrayList<>();
	
	public EADTO() {
	}

	public EADTO(int serialNo, MemberDTO member, Date date, String title, String content, int eaStatus, int saveNo,
			List<AddendumDTO> addendumList, List<EACarbonDTO> eaCarbonList, List<EAPathDTO> eaApprovalPathList,
			List<ApprovalerVO> approvalerList, List<ApprovalerVO> carbonerList) {
		this.serialNo = serialNo;
		this.member = member;
		this.date = date;
		this.title = title;
		this.content = content;
		this.eaStatus = eaStatus;
		this.saveNo = saveNo;
		this.addendumList = addendumList;
		this.eaCarbonList = eaCarbonList;
		this.eaApprovalPathList = eaApprovalPathList;
		this.approvalerList = approvalerList;
		this.carbonerList = carbonerList;
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

	public java.sql.Date getDate() {
		return date;
	}

	public void setDate(java.sql.Date date) {
		this.date = date;
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

	public int getEaStatus() {
		return eaStatus;
	}

	public void setEaStatus(int eaStatus) {
		this.eaStatus = eaStatus;
	}

	public int getSaveNo() {
		return saveNo;
	}

	public void setSaveNo(int saveNo) {
		this.saveNo = saveNo;
	}

	public List<AddendumDTO> getAddendumList() {
		return addendumList;
	}

	public void setAddendumList(List<AddendumDTO> addendumList) {
		this.addendumList = addendumList;
	}

	public List<EACarbonDTO> getEaCarbonList() {
		return eaCarbonList;
	}

	public void setEaCarbonList(List<EACarbonDTO> eaCarbonList) {
		this.eaCarbonList = eaCarbonList;
	}

	public List<EAPathDTO> getEaApprovalPathList() {
		return eaApprovalPathList;
	}

	public void setEaApprovalPathList(List<EAPathDTO> eaApprovalPathList) {
		this.eaApprovalPathList = eaApprovalPathList;
	}

	public List<ApprovalerVO> getApprovalerList() {
		return approvalerList;
	}

	public void setApprovalerList(List<ApprovalerVO> approvalerList) {
		this.approvalerList = approvalerList;
	}

	public List<ApprovalerVO> getCarbonerList() {
		return carbonerList;
	}

	public void setCarbonerList(List<ApprovalerVO> carbonerList) {
		this.carbonerList = carbonerList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "EADTO [serialNo=" + serialNo + ", member=" + member.getName() + ", date=" + date + ", title=" + title
				+ ", content=" + content + ", category=" + eaStatus + ", saveNo=" + saveNo + "]";
	}
	
	@Override
	public int compareTo(EADTO o) {
		if (o.getSerialNo() < this.serialNo) {
			return 1;
		} else  {
			return -1;
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this.getSerialNo() == ((EADTO)obj).getSerialNo()) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(serialNo);
	}
}
