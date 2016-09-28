package com.balancika.crm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="crm_opportunity_detail")
public class CrmOpportunityDetails implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="OP_D_ID")
	private int opDetailsId;
	
	@Column(name="OP_ID")
	private int opId;
	
	@Column(name="LineNo")
	private int lineNo;
	
	@Column(name="ItemID")
	private String itemId;
	
	@Column(name="UomID")
	private String uomId;
	
	@Column(name="LocationID")
	private String locationId;
	
	@Column(name="SalQty", columnDefinition = "double default 0.00")
	private double saleQty;
	
	@Column(name="UnitPrice", columnDefinition = "double default 0.00")
	private double unitPrice;

	@Column(name="TotalAmt", columnDefinition = "double default 0.00")
	private double totalAmt;
	
	@Column(name="NetTotalAmt", columnDefinition = "double default 0.00")
	private double netTotalAmt;
	
	@Column(name="DisDol", columnDefinition = "double default 0.00")
	private double disDol;
	
	@Column(name="DisPer", columnDefinition = "double default 0.00")
	private double disPer;
	
	@Column(name="STaxDol", columnDefinition = "double default 0.00")
	private double sTaxDol;
	
	@Column(name="STaxPer", columnDefinition = "double default 0.00")
	private double sTaxPer;
	
	@Column(name="VTaxDol", columnDefinition = "double default 0.00")
	private double vTaxDol;
	
	@Column(name="VTaxPer", columnDefinition = "double default 0.00")
	private double vTaxPer;
	
	@Column(name="ReportPrice", columnDefinition = "double default 0.00")
	private double reportPrice;
	
	@Column(name="Factor", columnDefinition = "double default 0.00")
	private double factor;
	
	@Column(name="ClassID", columnDefinition = "double default 0.00")
	private String classId;
	
	@Column(name="DisInv", columnDefinition = "double default 0.00")
	private double disInv;

	public int getOpDetailsId() {
		return opDetailsId;
	}

	public void setOpDetailsId(int opDetailsId) {
		this.opDetailsId = opDetailsId;
	}

	public int getOpId() {
		return opId;
	}

	public void setOpId(int opId) {
		this.opId = opId;
	}

	public int getLineNo() {
		return lineNo;
	}

	public void setLineNo(int lineNo) {
		this.lineNo = lineNo;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getUomId() {
		return uomId;
	}

	public void setUomId(String uomId) {
		this.uomId = uomId;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public double getSaleQty() {
		return saleQty;
	}

	public void setSaleQty(double saleQty) {
		this.saleQty = saleQty;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public double getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(double totalAmt) {
		this.totalAmt = totalAmt;
	}

	public double getNetTotalAmt() {
		return netTotalAmt;
	}

	public void setNetTotalAmt(double netTotalAmt) {
		this.netTotalAmt = netTotalAmt;
	}

	public double getDisDol() {
		return disDol;
	}

	public void setDisDol(double disDol) {
		this.disDol = disDol;
	}

	public double getDisPer() {
		return disPer;
	}

	public void setDisPer(double disPer) {
		this.disPer = disPer;
	}

	public double getsTaxDol() {
		return sTaxDol;
	}

	public void setsTaxDol(double sTaxDol) {
		this.sTaxDol = sTaxDol;
	}

	public double getsTaxPer() {
		return sTaxPer;
	}

	public void setsTaxPer(double sTaxPer) {
		this.sTaxPer = sTaxPer;
	}

	public double getvTaxDol() {
		return vTaxDol;
	}

	public void setvTaxDol(double vTaxDol) {
		this.vTaxDol = vTaxDol;
	}

	public double getvTaxPer() {
		return vTaxPer;
	}

	public void setvTaxPer(double vTaxPer) {
		this.vTaxPer = vTaxPer;
	}

	public double getReportPrice() {
		return reportPrice;
	}

	public void setReportPrice(double reportPrice) {
		this.reportPrice = reportPrice;
	}

	public double getFactor() {
		return factor;
	}

	public void setFactor(double factor) {
		this.factor = factor;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public double getDisInv() {
		return disInv;
	}

	public void setDisInv(double disInv) {
		this.disInv = disInv;
	}
}
