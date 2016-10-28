package com.balancika.crm.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;

import com.balancika.crm.utilities.LocalDateTimePersistenceConverter;


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
	
	@Convert(converter = LocalDateTimePersistenceConverter.class)
	@Column(name="N_CDate", updatable = false)
	private LocalDateTime noteCreateDate;
	
	@Transient
	private String createDate;
	
	@Transient
	private String createTime;
	
	@Transient
	private String createDateTime;
	
	@Transient
	private String noteRelatedName;
	
	@Column(name="N_MBy")
	private String noteModifiedBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="N_Mdate", insertable = true, updatable = true)
	private Date noteModifiedDate;
	
	@Transient
	private MeDataSource dataSource;

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
	
	public String getNoteModifiedBy() {
		return noteModifiedBy;
	}

	public LocalDateTime getNoteCreateDate() {
		return noteCreateDate;
	}

	public void setNoteCreateDate(LocalDateTime noteCreateDate) {
		this.noteCreateDate = noteCreateDate;
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

	/**
	 * @return the createDate
	 */
	public String getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getNoteRelatedName() {
		return noteRelatedName;
	}

	public void setNoteRelatedName(String noteRelatedName) {
		this.noteRelatedName = noteRelatedName;
	}

	public final MeDataSource getDataSource() {
		return dataSource;
	}

	public final void setDataSource(MeDataSource dataSource) {
		this.dataSource = dataSource;
	}
}
