package com.balancika.crm.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="crm_note")
public class CrmNote implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@NotEmpty
	@Column(name="N_ID", nullable = false)
	private String noteId;
	
	@NotEmpty
	@Column(name="N_Subject", nullable = false, unique = true)
	private String noteSubject;
	
	@Column(name="N_RToType")
	private String noteRelatedToModuleType;
	
	@Column(name="N_RToID")
	private String noteRelatedToModuleId;
	
	@Column(name="N_Des")
	private String noteDes;
	
	@Column(name="N_CBy", updatable = false)
	private String noteCreateBy;
	
	@Type(type = "date")
	@Column(name="N_CDate", updatable = false)
	private Date noteCreateDate;
	
	@Column(name="N_MBy")
	private String noteModifiedBy;
	
	@Type(type="date")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="N_Mdate", insertable = true, updatable = true)
	private Date noteModifiedDate;

	public String getNoteId() {
		return noteId;
	}

	public void setNoteId(String noteId) {
		this.noteId = noteId;
	}

	public String getNoteSubject() {
		return noteSubject;
	}

	public void setNoteSubject(String noteSubject) {
		this.noteSubject = noteSubject;
	}

	public String getNoteRelatedToModuleType() {
		return noteRelatedToModuleType;
	}

	public void setNoteRelatedToModuleType(String noteRelatedToModuleType) {
		this.noteRelatedToModuleType = noteRelatedToModuleType;
	}

	public String getNoteRelatedToModuleId() {
		return noteRelatedToModuleId;
	}

	public void setNoteRelatedToModuleId(String noteRelatedToModuleId) {
		this.noteRelatedToModuleId = noteRelatedToModuleId;
	}

	public String getNoteDes() {
		return noteDes;
	}

	public void setNoteDes(String noteDes) {
		this.noteDes = noteDes;
	}

	public String getNoteCreateBy() {
		return noteCreateBy;
	}

	public void setNoteCreateBy(String noteCreateBy) {
		this.noteCreateBy = noteCreateBy;
	}

	public Date getNoteCreateDate() {
		return noteCreateDate;
	}

	public void setNoteCreateDate(Date noteCreateDate) {
		this.noteCreateDate = noteCreateDate;
	}

	public String getNoteModifiedBy() {
		return noteModifiedBy;
	}

	public void setNoteModifiedBy(String noteModifiedBy) {
		this.noteModifiedBy = noteModifiedBy;
	}

	public Date getNoteModifiedDate() {
		return noteModifiedDate;
	}

	public void setNoteModifiedDate(Date noteModifiedDate) {
		this.noteModifiedDate = noteModifiedDate;
	}
	
}
