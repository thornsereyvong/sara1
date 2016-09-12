package com.balancika.crm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblcustomergroup")
public class CustomerGroup implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CustGroupID")
	private String custGroupId;
	
	@Column(name="CustGroupName")
	private String custGroupName;

	public String getCustGroupId() {
		return custGroupId;
	}

	public void setCustGroupId(String custGroupId) {
		this.custGroupId = custGroupId;
	}

	public String getCustGroupName() {
		return custGroupName;
	}

	public void setCustGroupName(String custGroupName) {
		this.custGroupName = custGroupName;
	}
}
