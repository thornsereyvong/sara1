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
@Table(name="crm_case_type")
public class CrmCaseType implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="CST_ID", nullable = false)
	private int caseTypeId;
	
	@NotEmpty
	@Column(name="CST_Name", nullable = false)
	private String caseTypeName;
	
	@Column(name="CST_Des")
	private String caseTypeDes;
	
	@Transient
	private MeDataSource dataSource;

	public int getCaseTypeId() {
		return caseTypeId;
	}

	public void setCaseTypeId(int caseTypeId) {
		this.caseTypeId = caseTypeId;
	}

	public String getCaseTypeName() {
		return caseTypeName;
	}

	public void setCaseTypeName(String caseTypeName) {
		this.caseTypeName = caseTypeName;
	}

	public String getCaseTypeDes() {
		return caseTypeDes;
	}

	public void setCaseTypeDes(String caseTypeDes) {
		this.caseTypeDes = caseTypeDes;
	}

	public final MeDataSource getDataSource() {
		return dataSource;
	}

	public final void setDataSource(MeDataSource dataSource) {
		this.dataSource = dataSource;
	}
	 
}
