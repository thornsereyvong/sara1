package com.balancika.crm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="HBUCustomer")
@Table(name="tblcustomer")
public class HBUCustomer implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "CustID")
	private String custId;
	
	@Column(name="CustName")
	private String custName;

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}
}
