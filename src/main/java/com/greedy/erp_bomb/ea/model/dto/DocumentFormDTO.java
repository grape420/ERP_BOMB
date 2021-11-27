package com.greedy.erp_bomb.ea.model.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(
		name = "DOCUMENT_SEQ_GENERATOR",
		sequenceName = "SEQ_DOCUMENT_CODE",
		initialValue = 1, allocationSize = 1)
@Table(name = "DOCUMENT_FORM")
public class DocumentFormDTO implements Serializable {
	private static final long serialVersionUID = -5810154735949217955L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	generator = "DOCUMENT_SEQ_GENERATOR")
	@Column(name = "DF_NO")
	private int no;
	
	@Column(name = "DF_FORM")
	private String form;

	@Column(name = "DF_NAME")
	private String name;

	public DocumentFormDTO() {
	}

	public DocumentFormDTO(int no, String form, String name) {
		this.no = no;
		this.form = form;
		this.name = name;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "DocumentFormDTO [no=" + no + ", form=" + form + ", name=" + name + "]";
	}
	
}
