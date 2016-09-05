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
@Table(name="crm_industry")
public class CrmIndustry implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="I_ID", unique = true, nullable = false)
	private int industID;
	
	@Column(name="I_Name", nullable = false)
	@NotEmpty
	private String industName;
	
	@Column(name="I_Des")
	private String description;

	public int getIndustID() {
		return industID;
	}

	public void setIndustID(int industID) {
		this.industID = industID;
	}

	public String getIndustName() {
		return industName;
	}

	public void setIndustName(String industName) {
		this.industName = industName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
