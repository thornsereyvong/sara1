package com.balancika.crm.model.report;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.balancika.crm.model.CrmLeadSource;
import com.balancika.crm.model.CrmOpportunityStage;
import com.balancika.crm.model.CrmOpportunityType;

@Entity(name="OpportunityModel")
@Table(name="crm_opportunity")
public class OpportunityModel implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="OP_ID")
	private String opId;
	
	@Column(name="OP_Name")
	private String opName;
	
	@Column(name="OP_Amt", nullable = false)
	private double opAmount;
	
	@Type(type="date")
	@Column(name="OP_CloseDate")
	private Date opCloseDate;

	@JoinColumn(name="OP_TypeID")
	@ManyToOne(optional = true, targetEntity = CrmOpportunityType.class)
	private CrmOpportunityType type;
	
	@JoinColumn(name = "OP_StageID", nullable = false)
	@ManyToOne(optional = false, targetEntity = CrmOpportunityStage.class)
	private CrmOpportunityStage stage;
	
	@Column(name = "OP_Probability")
	private int opProbability;
	
	@JoinColumn(name = "OP_LeadSourceID")
	@ManyToOne( optional = true, targetEntity = CrmLeadSource.class)
	private CrmLeadSource leadSource;
	
	@Column( name = "OP_NextStep")
	private String opNextStep;
	
	@Type(type="date")
	@Column(name= "OP_CDate", updatable = false)
	private Date opCreatedDate;
	
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

	public Date getOpCloseDate() {
		return opCloseDate;
	}

	public void setOpCloseDate(Date opCloseDate) {
		this.opCloseDate = opCloseDate;
	}

	public int getOpProbability() {
		return opProbability;
	}

	public void setOpProbability(int opProbability) {
		this.opProbability = opProbability;
	}

	public String getOpNextStep() {
		return opNextStep;
	}

	public void setOpNextStep(String opNextStep) {
		this.opNextStep = opNextStep;
	}

	public Date getOpCreatedDate() {
		return opCreatedDate;
	}

	public void setOpCreatedDate(Date opCreatedDate) {
		this.opCreatedDate = opCreatedDate;
	}

	public CrmOpportunityType getType() {
		return type;
	}

	public void setType(CrmOpportunityType type) {
		this.type = type;
	}

	public CrmOpportunityStage getStage() {
		return stage;
	}

	public void setStage(CrmOpportunityStage stage) {
		this.stage = stage;
	}

	public CrmLeadSource getLeadSource() {
		return leadSource;
	}

	public void setLeadSource(CrmLeadSource leadSource) {
		this.leadSource = leadSource;
	}

}
