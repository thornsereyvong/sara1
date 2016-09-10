package com.balancika.crm.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotEmpty;

import com.balancika.crm.utilities.LocalDateTimePersistenceConverter;

@Entity
@Table(name = "crm_collaboration")
public class CrmCollaboration implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CB_ID", nullable = false)
	private int colId;
	
	@NotEmpty
	@Column(name = "CB_Des", nullable = false)
	private String colDes;
	
	@Column(name="CB_UName", nullable = false)
	private String colUser;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="collapId")
	private List<CrmCollaborationTags> tags;
	
	@Convert(converter = LocalDateTimePersistenceConverter.class)
	@Column(name="CB_CreateDate")
	private LocalDateTime colCreateDate;

	public int getColId() {
		return colId;
	}

	public void setColId(int colId) {
		this.colId = colId;
	}

	public String getColDes() {
		return colDes;
	}

	public void setColDes(String colDes) {
		this.colDes = colDes;
	}

	public String getColUser() {
		return colUser;
	}

	public void setColUser(String colUser) {
		this.colUser = colUser;
	}

	public LocalDateTime getColCreateDate() {
		return colCreateDate;
	}

	public void setColCreateDate(LocalDateTime colCreateDate) {
		this.colCreateDate = colCreateDate;
	}

	public List<CrmCollaborationTags> getTags() {
		return tags;
	}

	public void setTags(List<CrmCollaborationTags> tags) {
		this.tags = tags;
	}
}
