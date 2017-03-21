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
@Table(name="tblsaleorder")
public class SaleOrder implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@NotEmpty
	@Column(name="SalID", nullable = false)
	private String saleId;
	
	@Column(name="SalType")
	private String saleType;

	@Column(name="SalReference")
	private String saleReference;
	
	@Type(type="date")
	@Column(name="SalDate", columnDefinition = "DATETIME")
	private Date saleDate;
	
	@Column(name="PeriodM", nullable=false, columnDefinition="int default 0")
	private Integer periodM;
	
	@Column(name="PeriodY", nullable=false, columnDefinition="int default 0")
	private Integer periodY;
	
	@Column(name="CustID")
	private String custId;
	
	@Column(name="PriceCode")
	private String priceCode;
	
	@Column(name="PostStatus")
	private String postStatus;
	
	@Column(name="PmtStatus")
	private String pmtStatus;
	
	@Column(name="JID", nullable=false, columnDefinition="int default 0")
	private Integer jId;
	
	@Column(name="PmtSchID")
	private String pmtSchId;
	
	@Column(name="EmpID")
	private String empId;
	
	@Column(name="TotalAmt", nullable=false, columnDefinition="Double default 0.00")
	private Double totalAmount;
	
	@Column(name="DisInvDol" , nullable=false, columnDefinition="Double default 0")
	private Double disInvDol;
	
	@Column(name="DisInvPer", nullable=false, columnDefinition="Double default 0")
	private Double disInvPer;
	
	@Column(name="TotalDis", nullable=false, columnDefinition="Double default 0")
	private Double totalDis;
	
	@Column(name="TotalSTax", nullable=false, columnDefinition="Double default 0")
	private Double totalSTax;
	
	@Column(name="TotalVTax", nullable=false, columnDefinition="Double default 0")
	private Double totalVTax;
	
	@Column(name="NetTotalAmt", nullable=false, columnDefinition="Double default 0")
	private Double netTotalAmt;
	
	@Column(name="PmtToDate", nullable=false, columnDefinition="Double default 0")
	private Double pmtToDate;
	
	@Column(name="cash", nullable=false, columnDefinition="Double default 0")
	private Double cash;
	
	@Column(name="CashCard", nullable=false, columnDefinition="Double default 0")
	private Double cashCard;
	
	@Column(name="CreditCard", nullable=false, columnDefinition="Double default 0")
	private Double creditCard;
	
	@Column(name="OtherSetAmt", columnDefinition="Double default 0")
	private Double otherSetAmt;
	
	@Column(name="ReceiveAR", nullable=false, columnDefinition="Double default 0")
	private Double receiveAR;
	
	@Type(type="date")
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
	
	@Type(type="date")
	@Column(name="DueDate", columnDefinition = "DATETIME")
	private Date dueDate;
	
	@Column(name="ClassID")
	private String classId;
	
	@Column(name="ShipTo")
	private String shipTo;
	
	@Column(name="IsRead")
	private Short isRead;
	
	@Transient
	private List<SaleOrderDetails> saleOrderDetails;
	
	@Transient
	private MeDataSource meDataSource;

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

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Double getDisInvDol() {
		return disInvDol;
	}

	public void setDisInvDol(Double disInvDol) {
		this.disInvDol = disInvDol;
	}

	public Double getDisInvPer() {
		return disInvPer;
	}

	public void setDisInvPer(Double disInvPer) {
		this.disInvPer = disInvPer;
	}

	public Double getTotalDis() {
		return totalDis;
	}

	public void setTotalDis(Double totalDis) {
		this.totalDis = totalDis;
	}

	public Double getTotalSTax() {
		return totalSTax;
	}

	public void setTotalSTax(Double totalSTax) {
		this.totalSTax = totalSTax;
	}

	public Double getTotalVTax() {
		return totalVTax;
	}

	public void setTotalVTax(Double totalVTax) {
		this.totalVTax = totalVTax;
	}

	public Double getNetTotalAmt() {
		return netTotalAmt;
	}

	public void setNetTotalAmt(Double netTotalAmt) {
		this.netTotalAmt = netTotalAmt;
	}

	public Double getPmtToDate() {
		return pmtToDate;
	}

	public void setPmtToDate(Double pmtToDate) {
		this.pmtToDate = pmtToDate;
	}

	public Double getCash() {
		return cash;
	}

	public void setCash(Double cash) {
		this.cash = cash;
	}

	public Double getCashCard() {
		return cashCard;
	}

	public void setCashCard(Double cashCard) {
		this.cashCard = cashCard;
	}

	public Double getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(Double creditCard) {
		this.creditCard = creditCard;
	}

	public Double getOtherSetAmt() {
		return otherSetAmt;
	}

	public void setOtherSetAmt(Double otherSetAmt) {		
		this.otherSetAmt = otherSetAmt;
	}

	public Double getReceiveAR() {
		return receiveAR;
	}

	public void setReceiveAR(Double receiveAR) {
		this.receiveAR = receiveAR;
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
	public String getShipTo() {
		return shipTo;
	}

	public void setShipTo(String shipTo) {
		this.shipTo = shipTo;
	}

	public Short getIsRead() {
		return isRead;
	}

	public void setIsRead(Short isRead) {
		this.isRead = isRead;
	}

	public List<SaleOrderDetails> getSaleOrderDetails() {
		return saleOrderDetails;
	}

	public void setSaleOrderDetails(List<SaleOrderDetails> saleOrderDetails) {
		this.saleOrderDetails = saleOrderDetails;
	}

	public final MeDataSource getMeDataSource() {
		return meDataSource;
	}

	public final void setMeDataSource(MeDataSource meDataSource) {
		this.meDataSource = meDataSource;
	}

}
