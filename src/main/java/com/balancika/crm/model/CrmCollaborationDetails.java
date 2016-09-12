package com.balancika.crm.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="crm_collaboration_details")
public class CrmCollaborationDetails implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CBS_ID")
	private int colDelId;
	
	@NotEmpty
	@Column(name="CBS_CBID")
	private int collapId;
	
	@NotEmpty
	@Column(name = "CBS_Des")
	private String colDelDes;

	@Column(name="CBS_UName")
	private String colDelUser;
	
	
	@Column(name="CBS_CreateDate")
	private LocalDateTime colDelCreateDate;
	
	@Transient
	private String createDate;


	public int getColDelId() {
		return colDelId;
	}

	public void setColDelId(int colDelId) {
		this.colDelId = colDelId;
	}

	public int getCollapId() {
		return collapId;
	}

	public void setCollapId(int collapId) {
		this.collapId = collapId;
	}

	public String getColDelDes() {
		return colDelDes;
	}

	public void setColDelDes(String colDelDes) {
		this.colDelDes = colDelDes;
	}

	public String getColDelUser() {
		return colDelUser;
	}


	public void setColDelUser(String colDelUser) {
		this.colDelUser = colDelUser;
	}

	public LocalDateTime getColDelCreateDate() {
		return colDelCreateDate;
	}

	public void setColDelCreateDate(LocalDateTime colDelCreateDate) {
		this.colDelCreateDate = colDelCreateDate;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
}
