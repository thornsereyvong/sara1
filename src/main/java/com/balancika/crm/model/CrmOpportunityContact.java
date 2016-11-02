package com.balancika.crm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "crm_opportunity_contact")
public class CrmOpportunityContact implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="OPCON_ID")
	private int opConId;

	@Column(name="OPCON_OPID")
	private String opId ;
	
	@Column(name = "OPCON_CONID")
	private String conId;
	
	@NotEmpty
	@Column(name="OPCON_Type")
	private String opConType;
	
	@Column(name="OPCON_Role")
	private String opConRole;
	
	@Transient
	private MeDataSource meDataSource;

	public final MeDataSource getMeDataSource() {
		return meDataSource;
	}

	public final void setMeDataSource(MeDataSource meDataSource) {
		this.meDataSource = meDataSource;
	}

	public int getOpConId() {
		return opConId;
	}

	public void setOpConId(int opConId) {
		this.opConId = opConId;
	}

	public String getOpId() {
		return opId;
	}

	public void setOpId(String opId) {
		this.opId = opId;
	}

	public String getConId() {
		return conId;
	}

	public void setConId(String conId) {
		this.conId = conId;
	}

	public String getOpConType() {
		return opConType;
	}

	public void setOpConType(String opConType) {
		this.opConType = opConType;
	}

	public String getOpConRole() {
		return opConRole;
	}

	public void setOpConRole(String opConRole) {
		this.opConRole = opConRole;
	}
}
