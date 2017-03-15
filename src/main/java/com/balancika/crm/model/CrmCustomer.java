package com.balancika.crm.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotEmpty;

@Entity(name="CrmCustomer")
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
	
	@Column(name="AID")
	private String aId;
	
	@Column(name = "CreateFrom", updatable = false)
	private String createFrom;
	
	
	@Column(name = "TermCreditLimit", updatable=false)
	private double termCreditLimit;
	
	@Column(name="TermNetDueIn", columnDefinition = "int default 0")
	private int termNetDueIn;
	
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = CrmIndustry.class)
	@JoinColumn(name="CrmIndustID", nullable = true)
	private CrmIndustry industID;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = CrmAccountType.class)
	@JoinColumn(name="CrmAccountTypeID", nullable = true)
	@Fetch(FetchMode.JOIN)
	private CrmAccountType accountTypeID;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "PriceCode", nullable = true)
	private PriceCode priceCode;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="CustGroupID")
	private CustomerGroup custGroup;
	
	@Column(name="CrmApproval")
	private int approval;
	
	@Column(name="CrmImageName")
	private String imageName;
	
	@Transient
	private List<CrmCustomerDetails> custDetails;
	
	@Transient
	private List<CrmCase> cases;
	
	@Transient
	private List<CrmContact> contacts;
	
	@Transient
	private List<CrmOpportunity> opportunities;
	
	@Transient
	private List<CrmShipAddress> shipAddresses;
	
	@Transient
	private List<Quote> quotes;

	@Transient
	private List<SaleOrder> saleOrders;
	
	@Transient
	private MeDataSource meDataSource;
	
	
	public double getTermCreditLimit() {
		return termCreditLimit;
	}

	public void setTermCreditLimit(double termCreditLimit) {
		this.termCreditLimit = termCreditLimit;
	}
	
	
	
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
	
	public PriceCode getPriceCode() {
		return priceCode;
	}

	public void setPriceCode(PriceCode priceCode) {
		this.priceCode = priceCode;
	}

	public CustomerGroup getCustGroup() {
		return custGroup;
	}

	public void setCustGroup(CustomerGroup custGroup) {
		this.custGroup = custGroup;
	}

	/**
	 * @return the approval
	 */
	public int getApproval() {
		return approval;
	}

	/**
	 * @param approval the approval to set
	 */
	public void setApproval(int approval) {
		this.approval = approval;
	}

	/**
	 * @return the imageName
	 */
	public String getImageName() {
		return imageName;
	}

	/**
	 * @param imageName the imageName to set
	 */
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	/**
	 * @return the custDetails
	 */
	public List<CrmCustomerDetails> getCustDetails() {
		return custDetails;
	}

	/**
	 * @param custDetails the custDetails to set
	 */
	public void setCustDetails(List<CrmCustomerDetails> custDetails) {
		this.custDetails = custDetails;
	}

	public List<CrmCase> getCases() {
		return cases;
	}

	public void setCases(List<CrmCase> cases) {
		this.cases = cases;
	}

	public List<CrmContact> getContacts() {
		return contacts;
	}

	public void setContacts(List<CrmContact> contacts) {
		this.contacts = contacts;
	}

	public List<CrmOpportunity> getOpportunities() {
		return opportunities;
	}

	public void setOpportunities(List<CrmOpportunity> opportunities) {
		this.opportunities = opportunities;
	}

	public String getaId() {
		return aId;
	}

	public void setaId(String aId) {
		this.aId = aId;
	}

	public int getTermNetDueIn() {
		return termNetDueIn;
	}

	public void setTermNetDueIn(int termNetDueIn) {
		this.termNetDueIn = termNetDueIn;
	}

	public List<CrmShipAddress> getShipAddresses() {
		return shipAddresses;
	}

	public void setShipAddresses(List<CrmShipAddress> shipAddresses) {
		this.shipAddresses = shipAddresses;
	}
	

	public List<Quote> getQuotes() {
		return quotes;
	}

	public void setQuotes(List<Quote> quotes) {
		this.quotes = quotes;
	}

	public List<SaleOrder> getSaleOrders() {
		return saleOrders;
	}

	public void setSaleOrders(List<SaleOrder> saleOrders) {
		this.saleOrders = saleOrders;
	}

	public String getCreateFrom() {
		return createFrom;
	}

	public void setCreateFrom(String createFrom) {
		this.createFrom = createFrom;
	}

	public final MeDataSource getMeDataSource() {
		return meDataSource;
	}

	public final void setMeDataSource(MeDataSource meDataSource) {
		this.meDataSource = meDataSource;
	}	
}
