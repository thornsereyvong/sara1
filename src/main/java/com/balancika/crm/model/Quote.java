package com.balancika.crm.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="tblsbquote")
public class Quote implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@NotEmpty
	@Column(name="SalID", nullable = false)
	private String saleId;
	
	@Column(name="SalType")
	private String saleType;

	@Column(name="SalReference")
	private String saleReference;
	
	@Type(type = "date")
	@Column(name="SalDate", columnDefinition = "DATETIME")
	private Date saleDate;
	
	@Column(name="PeriodM")
	private int periodM;
	
	@Column(name="PeriodY")
	private int periodY;
	
	@Column(name="CustID")
	private String custId;
	
	@Column(name="PriceCode")
	private String priceCode;
	
	@Column(name="PostStatus")
	private String postStatus;
	
	@Column(name="PmtStatus")
	private String pmtStatus;
	
	@Column(name="JID")
	private int jId;
	
	@Column(name="PmtSchID")
	private String pmtSchId;
	
	@Column(name="EmpID")
	private String empId;
	
	@Column(name="TotalAmt")
	private double totalAmt;
	
	@Column(name="DisInvDol")
	private double disInvDol;
	
	@Column(name="DisInvPer")
	private double disInvPer;
	
	@Column(name="TotalDis")
	private double totalDis;
	
	@Column(name="TotalSTax")
	private double totalSTax;
	
	@Column(name="TotalVTax")
	private double totalVTax;
	
	@Column(name="NetTotalAmt")
	private double netTotalAmt;
	
	@Column(name="PmtToDate")
	private double pmtToDate;
	
	@Column(name="cash")
	private double cash;
	
	@Column(name="CashCard")
	private double cashCard;
	
	@Column(name="PmtDisDate", columnDefinition = "DATETIME")
	private Date pmtDisDate;
	
	@Column(name="Remark")
	private String remark;
	
	@Column(name="Message")
	private String message;
	
	@Column(name="ClientID")
	private String clientId;
	
	@Column(name="LocationID")
	private String locationId;
	
	@Type(type = "date")
	@Column(name="DueDate", columnDefinition = "DATETIME")
	private Date dueDate;
	
	@Type(type = "date")
	@Column(name="StartDate")
	private Date startDate;
	
	@Type(type = "date")
	@Column(name="ExpireDate")
	private Date expireDate;
	
	@Column(name="ClassID")
	private String classId;
	
	@Column(name="ShipTo")
	private int shipTo;
	
	@Transient
	private List<QuoteDetails> quoteDetails;

	public String getSaleId() {
		return saleId;
	}

	public void setSaleId(String saleId) {
		this.saleId = saleId;
	}

	public String getSaleType() {
		return saleType;
	}

	public void setSaleType(String saleType) {
		this.saleType = saleType;
	}

	public String getSaleReference() {
		return saleReference;
	}

	public void setSaleReference(String saleReference) {
		this.saleReference = saleReference;
	}

	public Date getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}

	public int getPeriodM() {
		return periodM;
	}

	public void setPeriodM(int periodM) {
		this.periodM = periodM;
	}

	public int getPeriodY() {
		return periodY;
	}

	public void setPeriodY(int periodY) {
		this.periodY = periodY;
	}


	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getPriceCode() {
		return priceCode;
	}

	public void setPriceCode(String priceCode) {
		this.priceCode = priceCode;
	}

	public String getPostStatus() {
		return postStatus;
	}

	public void setPostStatus(String postStatus) {
		this.postStatus = postStatus;
	}

	public String getPmtStatus() {
		return pmtStatus;
	}

	public void setPmtStatus(String pmtStatus) {
		this.pmtStatus = pmtStatus;
	}

	public int getjId() {
		return jId;
	}

	public void setjId(int jId) {
		this.jId = jId;
	}

	public String getPmtSchId() {
		return pmtSchId;
	}

	public void setPmtSchId(String pmtSchId) {
		this.pmtSchId = pmtSchId;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public double getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(double totalAmt) {
		this.totalAmt = totalAmt;
	}

	public double getDisInvDol() {
		return disInvDol;
	}

	public void setDisInvDol(double disInvDol) {
		this.disInvDol = disInvDol;
	}

	public double getDisInvPer() {
		return disInvPer;
	}

	public void setDisInvPer(double disInvPer) {
		this.disInvPer = disInvPer;
	}

	public double getTotalDis() {
		return totalDis;
	}

	public void setTotalDis(double totalDis) {
		this.totalDis = totalDis;
	}

	public double getTotalSTax() {
		return totalSTax;
	}

	public void setTotalSTax(double totalSTax) {
		this.totalSTax = totalSTax;
	}

	public double getTotalVTax() {
		return totalVTax;
	}

	public void setTotalVTax(double totalVTax) {
		this.totalVTax = totalVTax;
	}

	public double getNetTotalAmt() {
		return netTotalAmt;
	}

	public void setNetTotalAmt(double netTotalAmt) {
		this.netTotalAmt = netTotalAmt;
	}

	public double getPmtToDate() {
		return pmtToDate;
	}

	public void setPmtToDate(double pmtToDate) {
		this.pmtToDate = pmtToDate;
	}

	public double getCash() {
		return cash;
	}

	public void setCash(double cash) {
		this.cash = cash;
	}

	public double getCashCard() {
		return cashCard;
	}

	public void setCashCard(double cashCard) {
		this.cashCard = cashCard;
	}

	public Date getPmtDisDate() {
		return pmtDisDate;
	}

	public void setPmtDisDate(Date pmtDisDate) {
		this.pmtDisDate = pmtDisDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public int getShipTo() {
		return shipTo;
	}

	public void setShipTo(int shipTo) {
		this.shipTo = shipTo;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public List<QuoteDetails> getQuoteDetails() {
		return quoteDetails;
	}

	public void setQuoteDetails(List<QuoteDetails> quoteDetails) {
		this.quoteDetails = quoteDetails;
	}

}
