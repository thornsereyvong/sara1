package com.balancika.crm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "crm_opportunity_stage")
public class CrmOpportunityStage implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "OS_ID", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int osId; // opportunity stage id
	
	@NotEmpty
	@Column(name="OS_Name", nullable = false, unique = true)
	private String osName; //Opportunity Stage Name
	
	@Column(name = "OS_Des")
	private String osDes;  //Opportunity stage description
	
	public int getOsId() {
		return osId;
	}

	public void setOsId(int osId) {
		this.osId = osId;
	}

	public String getOsName() {
		return osName;
	}

	public void setOsName(String osName) {
		this.osName = osName;
	}

	public String getOsDes() {
		return osDes;
	}

	public void setOsDes(String osDes) {
		this.osDes = osDes;
	}
	
}
