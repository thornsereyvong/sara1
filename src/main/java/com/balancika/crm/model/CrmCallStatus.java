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
@Table(name="crm_call_status")
public class CrmCallStatus implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="CLS_ID", nullable = false, unique = true)
	private int callStatusId;
	
	@NotEmpty
	@Column(name="CLS_Name", nullable = false, unique = true)
	private String callStatusName;
	
	@Column(name="CLS_Des")
	private String callStatusDes;
	
	@Transient
	private MeDataSource meDataSource;

	public int getCallStatusId() {
		return callStatusId;
	}

	public void setCallStatusId(int callStatusId) {
		this.callStatusId = callStatusId;
	}

	public String getCallStatusName() {
		return callStatusName;
	}

	public void setCallStatusName(String callStatusName) {
		this.callStatusName = callStatusName;
	}

	public String getCallStatusDes() {
		return callStatusDes;
	}

	public void setCallStatusDes(String callStatusDes) {
		this.callStatusDes = callStatusDes;
	}

	public final MeDataSource getMeDataSource() {
		return meDataSource;
	}

	public final void setMeDataSource(MeDataSource meDataSource) {
		this.meDataSource = meDataSource;
	}
	
}
