package com.balancika.crm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="crm_collaboration_tags")
public class CrmCollaborationTags implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="tageId")
	private int tagId;
	
	@Column(name="username")
	private String username;
	
	
	@JoinColumn(name="collapId")
	private int collapId;

	public int getTagId() {
		return tagId;
	}

	public void setTagId(int tagId) {
		this.tagId = tagId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getCollapId() {
		return collapId;
	}

	public void setCollapId(int collapId) {
		this.collapId = collapId;
	}
}
