package com.balancika.crm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="tblcustomer")
public class CrmCustomer implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@NotEmpty
	@Column(name="CustID", unique = true, nullable = false)
	private String custID;
	
	@NotEmpty
	@Column(name="CustName", unique = true, nullable = false)
	private String custName;
	
	@Column(name="CustTel1")
	private String custTel1;
	
	@Column(name="CustTel2")
	private String custTel2;
	
	@Column(name="CustFax")
	private String custFax;
	
	@Column(name="CustEmail")
	private String custEmail;
	
	@Column(name="custWebsite")
	private String custWebsite;
	
	@Column(name="CustAddress")
	private String custAddress;
	
	@Column(name="Facebook")
	private String facebook;
	
	@Column(name="Line")
	private String line;
	
	@Column(name="Viber")
	private String viber;
	
	@Column(name="WhatApp")
	private String whatApp;
	
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = CrmIndustry.class)
	@JoinColumn(name="CrmIndustID", nullable = true)
	private CrmIndustry industID;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = CrmAccountType.class)
	@JoinColumn(name="CrmAccountTypeID", nullable = true)
	@Fetch(FetchMode.JOIN)
	private CrmAccountType accountTypeID;
	
	@Column(name = "")
	private String priceCode;
	
	private String custGroupId;
	
	public String getCustID() {
		return custID;
	}

	public void setCustID(String custID) {
		this.custID = custID;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustTel1() {
		return custTel1;
	}

	public void setCustTel1(String custTel1) {
		this.custTel1 = custTel1;
	}

	public String getCustTel2() {
		return custTel2;
	}

	public void setCustTel2(String custTel2) {
		this.custTel2 = custTel2;
	}

	public String getCustFax() {
		return custFax;
	}

	public void setCustFax(String custFax) {
		this.custFax = custFax;
	}

	public String getCustEmail() {
		return custEmail;
	}

	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}

	public String getCustWebsite() {
		return custWebsite;
	}

	public void setCustWebsite(String custWebsite) {
		this.custWebsite = custWebsite;
	}

	public String getCustAddress() {
		return custAddress;
	}

	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getViber() {
		return viber;
	}

	public void setViber(String viber) {
		this.viber = viber;
	}

	public String getWhatApp() {
		return whatApp;
	}

	public void setWhatApp(String whatApp) {
		this.whatApp = whatApp;
	}

	public CrmIndustry getIndustID() {
		return industID;
	}

	public void setIndustID(CrmIndustry industID) {
		this.industID = industID;
	}

	public CrmAccountType getAccountTypeID() {
		return accountTypeID;
	}

	public void setAccountTypeID(CrmAccountType accountTypeID) {
		this.accountTypeID = accountTypeID;
	}

}
