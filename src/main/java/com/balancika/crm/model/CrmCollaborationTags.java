package com.balancika.crm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name="crm_colllaboration_tags")
public class CrmCollaborationTags implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="tagId")
	private int tagId;
	
	@Column(name="username")
	private String username;
	
	@Autowired
	private MeDataSource dataSource;
	
	/*@ManyToOne
	@JoinColumn(name="collapId", insertable=false, updatable=false, nullable=false)
	private CrmCollaboration collaboration;*/

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

	public final MeDataSource getDataSource() {
		return dataSource;
	}

	public final void setDataSource(MeDataSource dataSource) {
		this.dataSource = dataSource;
	}
}
