package com.balancika.crm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tblcustomerdetails")
public class CrmCustomerDetails implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="AID")
	private int aId;
	
	@Column(name="CustID")
	private String custId;
	
	@Column(name="Address")
	private String address;

	/**
	 * @return the aId
	 */
	public int getaId() {
		return aId;
	}

	/**
	 * @param aId the aId to set
	 */
	public void setaId(int aId) {
		this.aId = aId;
	}

	/**
	 * @return the custId
	 */
	public String getCustId() {
		return custId;
	}

	/**
	 * @param custId the custId to set
	 */
	public void setCustId(String custId) {
		this.custId = custId;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
}
