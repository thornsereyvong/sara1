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
@Table(name="crm_opportunity_type")
public class CrmOpportunityType implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="OT_ID", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int otId;
	
	@NotEmpty
	@Column(name="OT_Name", nullable = false, unique = true)
	private String otName;
	
	@Column(name = "OT_Des")
	private String otDes;
	
	@Transient
	private MeDataSource meDataSource;

	public int getOtId() {
		return otId;
	}

	public void setOtId(int otId) {
		this.otId = otId;
	}

	public String getOtName() {
		return otName;
	}

	public void setOtName(String otName) {
		this.otName = otName;
	}

	public String getOtDes() {
		return otDes;
	}

	public void setOtDes(String otDes) {
		this.otDes = otDes;
	}

	public final MeDataSource getMeDataSource() {
		return meDataSource;
	}

	public final void setMeDataSource(MeDataSource meDataSource) {
		this.meDataSource = meDataSource;
	}
}
