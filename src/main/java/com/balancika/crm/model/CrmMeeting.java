package com.balancika.crm.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="crm_meeting")
public class CrmMeeting implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@NotEmpty
	@Column(name="M_ID", unique = true, nullable = false)
	private String meetingId;
	
	@NotEmpty
	@Column(name="M_Subject", unique = true, nullable = false)
	private String meetingSubject;
	
	@Type(type="date")
	@Column(name="M_StartDate", nullable = false)
	private Date meetingStartDate;
	
	@Type(type="date")
	@Column(name="M_EndDate", nullable = false)
	private Date meetingEndDate;
	
	@Column(name="M_Duration")
	private String meetingDuration;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="M_StatusID", nullable = true)
	private CrmMeetingStatus meetingStatus;
	
	@Column(name="M_RToType")
	private String meetingRelatedToModuleType;
	
	@Column(name="M_RToID")
	private String meetingRelatedToModuleId;
	
	@Column(name="M_Location")
	private String meetingLocation;
	
	@Column(name="M_Des")
	private String meetingDes;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="M_ATo", nullable = true)
	private CrmUser meetingAssignTo;
	
	@Column(name="M_CBy", updatable = false)
	private String meetingCreateBy;
	
	@Type(type="date")
	@Column(name="M_CDate", updatable = false)
	private Date meetingCreateDate;
	
	@Column(name="M_MBy")
	private String meetingModifiedBy;
	
	@Type(type="date")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="M_MDate", insertable = false, updatable= false)
	private Date meetingModifiedDate;

	public String getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}

	public String getMeetingSubject() {
		return meetingSubject;
	}

	public void setMeetingSubject(String meetingSubject) {
		this.meetingSubject = meetingSubject;
	}

	public Date getMeetingStartDate() {
		return meetingStartDate;
	}

	public void setMeetingStartDate(Date meetingStartDate) {
		this.meetingStartDate = meetingStartDate;
	}

	public Date getMeetingEndDate() {
		return meetingEndDate;
	}

	public void setMeetingEndDate(Date meetingEndDate) {
		this.meetingEndDate = meetingEndDate;
	}

	public String getMeetingDuration() {
		return meetingDuration;
	}

	public void setMeetingDuration(String meetingDuration) {
		this.meetingDuration = meetingDuration;
	}

	public CrmMeetingStatus getMeetingStatus() {
		return meetingStatus;
	}

	public void setMeetingStatus(CrmMeetingStatus meetingStatus) {
		this.meetingStatus = meetingStatus;
	}

	public String getMeetingRelatedToModuleType() {
		return meetingRelatedToModuleType;
	}

	public void setMeetingRelatedToModuleType(String meetingRelatedToModuleType) {
		this.meetingRelatedToModuleType = meetingRelatedToModuleType;
	}

	public String getMeetingRelatedToModuleId() {
		return meetingRelatedToModuleId;
	}

	public void setMeetingRelatedToModuleId(String meetingRelatedToModuleId) {
		this.meetingRelatedToModuleId = meetingRelatedToModuleId;
	}

	public String getMeetingLocation() {
		return meetingLocation;
	}

	public void setMeetingLocation(String meetingLocation) {
		this.meetingLocation = meetingLocation;
	}

	public String getMeetingDes() {
		return meetingDes;
	}

	public void setMeetingDes(String meetingDes) {
		this.meetingDes = meetingDes;
	}

	public CrmUser getMeetingAssignTo() {
		return meetingAssignTo;
	}

	public void setMeetingAssignTo(CrmUser meetingAssignTo) {
		this.meetingAssignTo = meetingAssignTo;
	}

	public String getMeetingCreateBy() {
		return meetingCreateBy;
	}

	public void setMeetingCreateBy(String meetingCreateBy) {
		this.meetingCreateBy = meetingCreateBy;
	}

	public Date getMeetingCreateDate() {
		return meetingCreateDate;
	}

	public void setMeetingCreateDate(Date meetingCreateDate) {
		this.meetingCreateDate = meetingCreateDate;
	}

	public String getMeetingModifiedBy() {
		return meetingModifiedBy;
	}

	public void setMeetingModifiedBy(String meetingModifiedBy) {
		this.meetingModifiedBy = meetingModifiedBy;
	}

	public Date getMeetingModifiedDate() {
		return meetingModifiedDate;
	}

	public void setMeetingModifiedDate(Date meetingModifiedDate) {
		this.meetingModifiedDate = meetingModifiedDate;
	}
}
