package com.balancika.crm.model.report;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.balancika.crm.model.CrmIndustry;
import com.balancika.crm.model.CrmLeadSource;
import com.balancika.crm.model.CrmLeadStatus;

@Entity(name="LeadReportModel")
@Table(name="crm_lead")
public class LeadReportModel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="LA_ID")
	private String leadId;
	
	@Column(name="LA_Salutation")
	private String salutation;
	
	@Column(name="LA_FirstName")
	private String firstName;
	
	@Column(name="LA_LastName")
	private String lastName;
	
	@Column(name="LA_AccountName")
	private String accountName;
	
	@Column(name="LA_Email")
	private String Email;
	
	//relationship many to one between table crm_lead and crm_lead_status 
	@ManyToOne(targetEntity = CrmLeadStatus.class, fetch = FetchType.EAGER) 
	@JoinColumn(name="LA_StatusID",
			nullable = true)
	private CrmLeadStatus status;
	
	/* relationship many to one between table crm_lead and crm_industry */
	@ManyToOne(fetch = FetchType.EAGER) 
	@JoinColumn(name="LA_IndustID", nullable = true)
	private CrmIndustry industry;
	
	/* relationship many to one between table crm_lead and crm_lead_soruce */
	@ManyToOne(fetch = FetchType.EAGER) 
	@JoinColumn(name="LA_SourceID", nullable = true)
	private CrmLeadSource source;
	
	@Column(name="LA_CDate")
	@Type(type = "date")
	private Date createdDate;
	
	public String getLeadId() {
		return leadId;
	}

	public void setLeadId(String leadId) {
		this.leadId = leadId;
	}

	public String getSalutation() {
		return salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public CrmLeadStatus getStatus() {
		return status;
	}

	public void setStatus(CrmLeadStatus status) {
		this.status = status;
	}

	public CrmIndustry getIndustry() {
		return industry;
	}

	public void setIndustry(CrmIndustry industry) {
		this.industry = industry;
	}

	public CrmLeadSource getSource() {
		return source;
	}

	public void setSource(CrmLeadSource source) {
		this.source = source;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}
}
