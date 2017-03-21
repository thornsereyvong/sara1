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
	private Integer periodM;
	
	@Column(name="PeriodY")
	private Integer periodY;
	
	@Column(name="CustID")
	private String custId;
	
	@Column(name="PriceCode")
	private String priceCode;
	
	@Column(name="PostStatus")
	private String postStatus;
	
	@Column(name="EmpID")
	private String empId;
	
	@Column(name="TotalAmt")
	private Double totalAmt;
	
	@Column(name="DisInvDol")
	private Double disInvDol;
	
	@Column(name="DisInvPer")
	private Double disInvPer;
	
	@Column(name="TotalDis")
	private Double totalDis;
	
	@Column(name="TotalSTax")
	private Double totalSTax;
	
	@Column(name="TotalVTax")
	private Double totalVTax;
	
	@Column(name="NetTotalAmt")
	private Double netTotalAmt;
	
	@Column(name="Remark")
	private String remark;
	
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
	private String shipTo;
	
	@Transient
	private List<QuoteDetails> quoteDetails;
	
	@Transient
	private MeDataSource meDataSource;

	public final MeDataSource getMeDataSource() {
		return meDataSource;
	}

	public final void setMeDataSource(MeDataSource meDataSource) {
		this.meDataSource = meDataSource;
	}

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

	public Integer getPeriodM() {
		return periodM;
	}

	public void setPeriodM(Integer periodM) {
		this.periodM = periodM;
	}

	public Integer getPeriodY() {
		return periodY;
	}

	public void setPeriodY(Integer periodY) {
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

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public Double getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(Double totalAmt) {
		this.totalAmt = totalAmt;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
