package com.balancika.crm.model;

import java.io.Serializable;
import java.time.LocalDateTime;
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
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

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
	
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm a")
	@Column(name="TA_StartDate")
	private LocalDateTime taskStartDate;
	
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm a")
	@Column(name="TA_DueDate")
	private LocalDateTime taskDueDate;
	
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
	
	@Type(type="date")
	@Column(name="TA_CDate", updatable = false)
	private Date taskCreateDate;
	
	@Column(name="TA_MBy")
	private String taskModifiedBy;
	
	@Type(type="date")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="TA_MDate", insertable = false, updatable = false)
	private Date taskModifiedDate;

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

	public Date getTaskCreateDate() {
		return taskCreateDate;
	}

	public void setTaskCreateDate(Date taskCreateDate) {
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
	
}
