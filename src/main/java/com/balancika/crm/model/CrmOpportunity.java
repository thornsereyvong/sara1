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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="crm_opportunity")
public class CrmOpportunity implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="OP_ID", unique = true, nullable = false)
	@NotEmpty
	private String opId;
	
	@NotEmpty
	@Column(name="OP_Name", nullable = false)
	private String opName;
	
	@Column(name="OP_Amt", nullable = false)
	private double opAmount;
	
	@JoinColumn(name="OP_CustID", nullable = false)
	@ManyToOne(targetEntity = CrmCustomer.class)
	private CrmCustomer customer;
	
	@Type(type="date")
	@Column(name="OP_CloseDate", nullable = false)
	private Date opCloseDate;

	@JoinColumn(name="OP_TypeID", nullable = true)
	@ManyToOne(optional = true, targetEntity = CrmOpportunityType.class)
	private CrmOpportunityType opTypeID;
	
	@JoinColumn(name = "OP_StageID", nullable = false)
	@ManyToOne(optional = false, targetEntity = CrmOpportunityStage.class)
	private CrmOpportunityStage opStageId;
	
	@Column(name = "OP_Probability")
	private int opProbability;
	
	@JoinColumn(name = "OP_LeadSourceID", nullable = true)
	@ManyToOne( optional = true, targetEntity = CrmLeadSource.class)
	private CrmLeadSource opLeadSourceID;
	
	@Column( name = "OP_NextStep")
	private String opNextStep;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "OP_CampID")
	private CrmCampaign opCampaign;
	
	@Column(name="OP_Des")
	private String opDes;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "OP_ATo")
	private CrmUser opAssignedTo;
	
	@Column(name = "OP_CBy", updatable = false)
	private String opCreateBy;
	
	@Type(type="date")
	@Column(name= "OP_CDate", updatable = false)
	private Date opCreateDate;
	
	@Column(name = "OP_MBy")
	private String opModifyBy;
	
	@Type(type="date")
	@Column(name = "OP_MDate", insertable = false, updatable = false)
	private Date opModifyDate;

	public String getOpId() {
		return opId;
	}

	public void setOpId(String opId) {
		this.opId = opId;
	}

	public String getOpName() {
		return opName;
	}

	public void setOpName(String opName) {
		this.opName = opName;
	}

	public double getOpAmount() {
		return opAmount;
	}

	public void setOpAmount(double opAmount) {
		this.opAmount = opAmount;
	}

	public CrmCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(CrmCustomer customer) {
		this.customer = customer;
	}

	public Date getOpCloseDate() {
		return opCloseDate;
	}

	public void setOpCloseDate(Date opCloseDate) {
		this.opCloseDate = opCloseDate;
	}

	public CrmOpportunityType getOpTypeID() {
		return opTypeID;
	}

	public void setOpTypeID(CrmOpportunityType opTypeID) {
		this.opTypeID = opTypeID;
	}

	public CrmOpportunityStage getOpStageId() {
		return opStageId;
	}

	public void setOpStageId(CrmOpportunityStage opStageId) {
		this.opStageId = opStageId;
	}

	public int getOpProbability() {
		return opProbability;
	}

	public void setOpProbability(int opProbability) {
		this.opProbability = opProbability;
	}

	public CrmLeadSource getOpLeadSourceID() {
		return opLeadSourceID;
	}

	public void setOpLeadSourceID(CrmLeadSource opLeadSourceID) {
		this.opLeadSourceID = opLeadSourceID;
	}

	public String getOpNextStep() {
		return opNextStep;
	}

	public void setOpNextStep(String opNextStep) {
		this.opNextStep = opNextStep;
	}

	public CrmCampaign getOpCampaign() {
		return opCampaign;
	}

	public void setOpCampId(CrmCampaign opCampaign) {
		this.opCampaign = opCampaign;
	}

	public String getOpDes() {
		return opDes;
	}

	public void setOpDes(String opDes) {
		this.opDes = opDes;
	}

	public CrmUser getOpAssignedTo() {
		return opAssignedTo;
	}

	public void setOpAssignedTo(CrmUser opAssignedTo) {
		this.opAssignedTo = opAssignedTo;
	}

	public String getOpCreateBy() {
		return opCreateBy;
	}

	public void setOpCreateBy(String opCreateBy) {
		this.opCreateBy = opCreateBy;
	}

	public Date getOpCreateDate() {
		return opCreateDate;
	}

	public void setOpCreateDate(Date opCreateDate) {
		this.opCreateDate = opCreateDate;
	}

	public String getOpModifyBy() {
		return opModifyBy;
	}

	public void setOpModifyBy(String opModifyBy) {
		this.opModifyBy = opModifyBy;
	}

	public Date getOpModifyDate() {
		return opModifyDate;
	}

	public void setOpModifyDate(Date opModifyDate) {
		this.opModifyDate = opModifyDate;
	}
	
}
