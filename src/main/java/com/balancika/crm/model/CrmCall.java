package com.balancika.crm.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

import com.balancika.crm.utilities.LocalDateTimePersistenceConverter;

@Entity
@Table(name = "crm_call")
public class CrmCall implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CL_ID", nullable = false, unique = true)
	private String callId;

	@NotEmpty
	@Column(name = "CL_Subject", nullable = false, unique = true)
	private String callSubject;

	@Convert(converter = LocalDateTimePersistenceConverter.class)
	@Column(name = "CL_SDate")
	private LocalDateTime callStartDate;

	@Transient
	private String startDate;

	@Column(name = "CL_Duration", nullable = false)
	private String callDuration;

	@ManyToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "CL_StatusID", nullable = false)
	private CrmCallStatus callStatus;

	@Column(name = "CL_RToType")
	private String callRelatedToModuleType;

	@Column(name = "CL_RToID")
	private String callRelatedToFieldId;

	@Column(name = "CL_Des")
	private String callDes;

	@ManyToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "CL_ATo", nullable = true)
	private CrmUser callAssignTo;

	@Column(name = "CL_CBy", updatable = false)
	private String callCreateBy;

	@Convert(converter = LocalDateTimePersistenceConverter.class)
	@Column(name = "CL_CDate", updatable = false)
	private LocalDateTime callCreateDate;

	@Column(name = "CL_MBy")
	private String callModifiedBy;

	@Type(type = "date")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CL_MDate", insertable = false, updatable = false)
	private Date callModifiedDate;

	@Transient
	private MeDataSource meDataSource;

	public String getCallId() {
		return callId;
	}

	public void setCallId(String callId) {
		this.callId = callId;
	}

	public String getCallSubject() {
		return callSubject;
	}

	public void setCallSubject(String callSubject) {
		this.callSubject = callSubject;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getCallStartDate() {
		return callStartDate;
	}

	public void setCallStartDate(LocalDateTime callStartDate) {
		this.callStartDate = callStartDate;
	}

	public String getCallDuration() {
		return callDuration;
	}

	public void setCallDuration(String callDuration) {
		this.callDuration = callDuration;
	}

	public CrmCallStatus getCallStatus() {
		return callStatus;
	}

	public void setCallStatus(CrmCallStatus callStatus) {
		this.callStatus = callStatus;
	}

	public String getCallRelatedToModuleType() {
		return callRelatedToModuleType;
	}

	public void setCallRelatedToModuleType(String callRelatedToModuleType) {
		this.callRelatedToModuleType = callRelatedToModuleType;
	}

	public String getCallRelatedToFieldId() {
		return callRelatedToFieldId;
	}

	public void setCallRelatedToFieldId(String callRelatedToFieldId) {
		this.callRelatedToFieldId = callRelatedToFieldId;
	}

	public String getCallDes() {
		return callDes;
	}

	public void setCallDes(String callDes) {
		this.callDes = callDes;
	}

	public CrmUser getCallAssignTo() {
		return callAssignTo;
	}

	public void setCallAssignTo(CrmUser callAssignTo) {
		this.callAssignTo = callAssignTo;
	}

	public String getCallCreateBy() {
		return callCreateBy;
	}

	public void setCallCreateBy(String callCreateBy) {
		this.callCreateBy = callCreateBy;
	}

	public LocalDateTime getCallCreateDate() {
		return callCreateDate;
	}

	public void setCallCreateDate(LocalDateTime callCreateDate) {
		this.callCreateDate = callCreateDate;
	}

	public String getCallModifiedBy() {
		return callModifiedBy;
	}

	public void setCallModifiedBy(String callModifiedBy) {
		this.callModifiedBy = callModifiedBy;
	}

	public Date getCallModifiedDate() {
		return callModifiedDate;
	}

	public void setCallModifiedDate(Date callModifiedDate) {
		this.callModifiedDate = callModifiedDate;
	}

	public final MeDataSource getMeDataSource() {
		return meDataSource;
	}

	public final void setMeDataSource(MeDataSource meDataSource) {
		this.meDataSource = meDataSource;
	}

}
