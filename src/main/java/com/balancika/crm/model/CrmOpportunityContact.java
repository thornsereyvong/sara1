package com.balancika.crm.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "crm_opportunity_contact")
public class CrmOpportunityContact implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="OPCON_ID")
	private int opportunityContactId;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="OPCON_OPID")
	private CrmOpportunity conOpportunity;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "OPCON_CONID")
	private CrmContact opContact;
	
	@NotEmpty
	@Column(name="OPCON_Type")
	private String opportunityContactType;
	
	@Column(name="OPCON_Role")
	private String opportunityContactRole;

	public int getOpportunityContactId() {
		return opportunityContactId;
	}

	public void setOpportunityContactId(int opportunityContactId) {
		this.opportunityContactId = opportunityContactId;
	}

	public CrmOpportunity getConOpportunity() {
		return conOpportunity;
	}

	public void setConOpportunity(CrmOpportunity conOpportunity) {
		this.conOpportunity = conOpportunity;
	}

	public CrmContact getOpContact() {
		return opContact;
	}

	public void setOpContact(CrmContact opContact) {
		this.opContact = opContact;
	}

	public String getOpportunityContactType() {
		return opportunityContactType;
	}

	public void setOpportunityContactType(String opportunityContactType) {
		this.opportunityContactType = opportunityContactType;
	}

	public String getOpportunityContactRole() {
		return opportunityContactRole;
	}

	public void setOpportunityContactRole(String opportunityContactRole) {
		this.opportunityContactRole = opportunityContactRole;
	}
}
