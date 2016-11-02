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
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "crm_camp")
public class CrmCampaign implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CA_ID", unique = true, nullable = false)
	@NotEmpty
	private String campID;

	@Column(name = "CA_Name", unique = true, nullable = false)
	@NotEmpty
	private String campName;

	@Column(name = "CA_SDate")
	@Type(type = "date")
	private Date startDate;

	@Type(type = "date")
	@Column(name = "CA_EDate", nullable = false)
	private Date endDate;

	/* relationship Many to one between table crm_camp and crm_camp_status */
	@ManyToOne(optional = false, targetEntity = CrmCampaignStatus.class)
	@JoinColumn(name = "CA_StatusID",
				nullable = false)
	private CrmCampaignStatus status;

	/* relationship one to one between table crm_camp and crm_camp_type */
	@ManyToOne(optional = false, targetEntity = CrmCampaignType.class)
	@JoinColumn(name = "CA_TypeID",nullable = false)
	private CrmCampaignType type;

	@Column(name = "CA_Des")
	private String description;

	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "CA_ParentID", nullable = true)
	private CrmCampaign parent;

	@ManyToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "CA_ATo", nullable = true)
	private CrmUser assignTo;

	@Column(name = "CA_Budget")
	private double budget;

	@Column(name = "CA_ActualCost")
	private double actualCost;

	@Column(name = "CA_ExpectedCost")
	private double expectedCost;

	@Column(name = "CA_ExpectedRevenue")
	private double expectedRevenue;

	@Column(name = "CA_NumSend")
	private int numSend;

	@Column(name = "CA_ExpectedResponse")
	private int expectedResponse;

	@Column(name = "CA_CBy", updatable = false)
	private String createdBy;

	@Type(type = "date")
	@Column(name = "CA_CDate", updatable = false)
	private Date createdDate;

	@Column(name = "CA_MDate", updatable = false, insertable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@Type(type = "date")
	private Date modifiedDate;
	
	@Transient
	private MeDataSource meDataSource;

	@Column(name = "CA_MBy")
	private String modifiedBy;
	
	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public CrmCampaignStatus getStatus() {
		return status;
	}

	public void setStatus(CrmCampaignStatus status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CrmCampaign getParent() {
		return parent;
	}

	public void setParent(CrmCampaign parent) {
		this.parent = parent;
	}

	public CrmUser getAssignTo() {
		return assignTo;
	}

	public void setAssignTo(CrmUser assignTo) {
		this.assignTo = assignTo;
	}

	public double getBudget() {
		return budget;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}

	public double getActualCost() {
		return actualCost;
	}

	public void setActualCost(double actualCost) {
		this.actualCost = actualCost;
	}

	public double getExpectedCost() {
		return expectedCost;
	}

	public void setExpectedCost(double expectedCost) {
		this.expectedCost = expectedCost;
	}

	public double getExpectedRevenue() {
		return expectedRevenue;
	}

	public void setExpectedRevenue(double expectedRevenue) {
		this.expectedRevenue = expectedRevenue;
	}

	public int getNumSend() {
		return numSend;
	}

	public void setNumSend(int numSend) {
		this.numSend = numSend;
	}

	public int getExpectedResponse() {
		return expectedResponse;
	}

	public void setExpectedResponse(int expectedResponse) {
		this.expectedResponse = expectedResponse;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public void setCampName(String campName) {
		this.campName = campName;
	}

	public String getCampID() {
		return campID;
	}

	public void setCampID(String campId) {
		this.campID = campId;
	}

	public String getCampName() {
		return campName;
	}

	public void setName(String campName) {
		this.campName = campName;
	}
	
	public CrmCampaignType getType() {
		return type;
	}

	public void setType(CrmCampaignType type) {
		this.type = type;
	}

	public final MeDataSource getMeDataSource() {
		return meDataSource;
	}

	public final void setMeDataSource(MeDataSource meDataSource) {
		this.meDataSource = meDataSource;
	}
}
