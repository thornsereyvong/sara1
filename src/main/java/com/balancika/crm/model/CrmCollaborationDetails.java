package com.balancika.crm.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="crm_collaboration_details")
public class CrmCollaborationDetails implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CBS_ID")
	private int colDelId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="CBS_CBID")
	private CrmCollaboration collaboration;
	
	@NotEmpty
	@Column(name = "CBS_Des")
	private String colDelDes;

	@Column(name="CBS_UName")
	private String colDelUser;
	
	
	@Column(name="CBS_CreateDate")
	private LocalDateTime colDelCreateDate;


	public int getColDelId() {
		return colDelId;
	}


	public void setColDelId(int colDelId) {
		this.colDelId = colDelId;
	}


	public CrmCollaboration getCollaboration() {
		return collaboration;
	}


	public void setCollaboration(CrmCollaboration collaboration) {
		this.collaboration = collaboration;
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
	
}
