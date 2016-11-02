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
@Table(name="crm_task")
public class CrmTask implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@NotEmpty
	@Column(name="TA_ID", nullable = false, unique = true)
	private String taskId;
	
	@NotEmpty
	@Column(name="TA_Subject", nullable = false, unique = true)
	private String taskSubject;
	
	@Convert(converter = LocalDateTimePersistenceConverter.class)
	@Column(name="TA_StartDate")
	private LocalDateTime taskStartDate;
	
	@Convert(converter = LocalDateTimePersistenceConverter.class)
	@Column(name="TA_DueDate")
	private LocalDateTime taskDueDate;
	
	@Transient
	private String startDate;

	@Transient
	private String dueDate;
	
	@NotEmpty
	@Column(name="TA_Priority", nullable = false)
	private String taskPriority;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="TA_StatusID", nullable = false)
	private CrmTaskStatus taskStatus;
	
	@Column(name="TA_Des")
	private String taskDes;
	
	@Column(name="TA_RToType")
	private String taskRelatedToModule;
	
	@Column(name="TA_RToID")
	private String taskRelatedToId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="TA_ContactID")
	private CrmContact taskContact;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="TA_ATo")
	private CrmUser taskAssignTo;
	
	@Column(name="TA_CBy", updatable = false)
	private String taskCreateBy;
	
	@Convert(converter = LocalDateTimePersistenceConverter.class)
	@Column(name="TA_CDate", updatable = false)
	private LocalDateTime taskCreateDate;
	
	@Column(name="TA_MBy")
	private String taskModifiedBy;
	
	@Type(type="date")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="TA_MDate", insertable = false, updatable = false)
	private Date taskModifiedDate;
	
	@Transient
	private MeDataSource meDataSource;

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskSubject() {
		return taskSubject;
	}

	public void setTaskSubject(String taskSubject) {
		this.taskSubject = taskSubject;
	}

	public LocalDateTime getTaskStartDate() {
		return taskStartDate;
	}

	public void setTaskStartDate(LocalDateTime taskStartDate) {
		this.taskStartDate = taskStartDate;
	}

	public LocalDateTime getTaskDueDate() {
		return taskDueDate;
	}

	public void setTaskDueDate(LocalDateTime taskDueDate) {
		this.taskDueDate = taskDueDate;
	}

	public String getTaskPriority() {
		return taskPriority;
	}

	public void setTaskPriority(String taskPriority) {
		this.taskPriority = taskPriority;
	}

	public CrmTaskStatus getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(CrmTaskStatus taskStatus) {
		this.taskStatus = taskStatus;
	}

	public String getTaskDes() {
		return taskDes;
	}

	public void setTaskDes(String taskDes) {
		this.taskDes = taskDes;
	}

	public String getTaskRelatedToModule() {
		return taskRelatedToModule;
	}

	public void setTaskRelatedToModule(String taskRelatedToModule) {
		this.taskRelatedToModule = taskRelatedToModule;
	}

	public String getTaskRelatedToId() {
		return taskRelatedToId;
	}

	public void setTaskRelatedToId(String taskRelatedToId) {
		this.taskRelatedToId = taskRelatedToId;
	}

	public CrmContact getTaskContact() {
		return taskContact;
	}

	public void setTaskContact(CrmContact taskContact) {
		this.taskContact = taskContact;
	}

	public CrmUser getTaskAssignTo() {
		return taskAssignTo;
	}

	public void setTaskAssignTo(CrmUser taskAssignTo) {
		this.taskAssignTo = taskAssignTo;
	}

	public String getTaskCreateBy() {
		return taskCreateBy;
	}

	public void setTaskCreateBy(String taskCreateBy) {
		this.taskCreateBy = taskCreateBy;
	}

	public LocalDateTime getTaskCreateDate() {
		return taskCreateDate;
	}

	public void setTaskCreateDate(LocalDateTime taskCreateDate) {
		this.taskCreateDate = taskCreateDate;
	}

	public String getTaskModifiedBy() {
		return taskModifiedBy;
	}

	public void setTaskModifiedBy(String taskModifiedBy) {
		this.taskModifiedBy = taskModifiedBy;
	}

	public Date getTaskModifiedDate() {
		return taskModifiedDate;
	}

	public void setTaskModifiedDate(Date taskModifiedDate) {
		this.taskModifiedDate = taskModifiedDate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public final MeDataSource getMeDataSource() {
		return meDataSource;
	}

	public final void setMeDataSource(MeDataSource meDataSource) {
		this.meDataSource = meDataSource;
	}
}
