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
@Table(name="crm_case")
public class CrmCase implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@NotEmpty
	@Column(name="CS_ID", nullable = false)
	private String caseId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="CS_StatusID", nullable = false)
	private CrmCaseStatus status;

	@ManyToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="CS_TypeID", nullable = false)
	private CrmCaseType type;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "CS_Priority", nullable = false)
	private CrmCasePriority priority;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="CS_CustID", nullable = true)
	private CrmCustomer customer;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="CS_ContactID", nullable = true)
	private CrmContact contact;
	
	@Column(name="CS_Subject", unique = true)
	private String subject;
	
	@Column(name="CS_Des")
	private String des;
	
	@Column(name="CS_Resolution")
	private String resolution;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="CS_ATo")
	private CrmUser assignTo;
	
	@Column(name="CS_CBy", updatable = true)
	private String createBy;
	
	@Type(type="date")
	@Column(name="CS_CDate", updatable = false)
	private Date createDate;
	
	@Column(name="CS_MBy")
	private String modifyBy;
	
	@Type(type = "date")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CS_MDate", insertable = false, updatable = false)
	private Date modifyDate;

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public CrmCaseStatus getStatus() {
		return status;
	}

	public void setStatus(CrmCaseStatus status) {
		this.status = status;
	}

	public CrmCaseType getType() {
		return type;
	}

	public void setType(CrmCaseType type) {
		this.type = type;
	}

	public CrmCasePriority getPriority() {
		return priority;
	}

	public void setPriority(CrmCasePriority priority) {
		this.priority = priority;
	}

	public CrmCustomer getCaseCustomer() {
		return customer;
	}

	public void setCustomer(CrmCustomer customer) {
		this.customer = customer;
	}

	public CrmContact getContact() {
		return contact;
	}

	public void setContact(CrmContact contact) {
		this.contact = contact;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public CrmUser getAssignTo() {
		return assignTo;
	}

	public void setAssignTo(CrmUser assignTo) {
		this.assignTo = assignTo;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
}
