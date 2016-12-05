package com.balancika.crm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="crm_case_detail")
@DynamicInsert
@DynamicUpdate
public class CrmCaseDetail implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="CSD_ID")
	private int caseDetailId;
	
	@Column(name="CS_ID")
	private String caseId;
	
	@Column(name="LineNo", columnDefinition = "int default 0")
	private int lineNo;
	
	@Column(name="ItemID")
	private String itemID;
	
	@Column(name="UOM")
	private String uom;
	
	@Column(name="LocationID")
	private String locationId;
	
	@Column(name="SalQty", columnDefinition = "double default 0.00")
	private double salQty;
	
	@Column(name="UnitPrice", columnDefinition = "double default 0.00")
	private double unitPrice;
	
	@Column(name="TotalAmt", columnDefinition = "double default 0.00")
	private double totalAmt;
	
	@Column(name="DisDol", columnDefinition = "double default 0.00")
	private double disDol;
	
	@Column(name="DisPer", columnDefinition = "double default 0.00")
	private double disPer;
	
	@Column(name="VTaxDol", columnDefinition = "double default 0.00")
	private double vTaxDol;
	
	@Column(name="VTaxPer", columnDefinition = "double default 0.00")
	private double vTaxPer;
	
	@Column(name="STaxDol", columnDefinition = "double default 0.00")
	private double sTaxDol;
	
	@Column(name="STaxPer", columnDefinition = "double default 0.00")
	private double sTaxPer;
	
	@Column(name="DisInv", columnDefinition = "double default 0.00")
	private double disInv;
	
	@Column(name="NetTotalAmt", columnDefinition = "double default 0.00")
	private double netTotalAmt;
	
	@Transient
	private MeDataSource meDataSource;

	public int getCaseDetailId() {
		return caseDetailId;
	}

	public void setCaseDetailId(int caseDetailId) {
		this.caseDetailId = caseDetailId;
	}

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public int getLineNo() {
		return lineNo;
	}

	public void setLineNo(int lineNo) {
		this.lineNo = lineNo;
	}

	public String getItemID() {
		return itemID;
	}

	public void setItemID(String itemID) {
		this.itemID = itemID;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public double getSalQty() {
		return salQty;
	}

	public void setSalQty(double salQty) {
		this.salQty = salQty;
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

	public double getDisInv() {
		return disInv;
	}

	public void setDisInv(double disInv) {
		this.disInv = disInv;
	}

	public double getNetTotalAmt() {
		return netTotalAmt;
	}

	public void setNetTotalAmt(double netTotalAmt) {
		this.netTotalAmt = netTotalAmt;
	}

	public MeDataSource getMeDataSource() {
		return meDataSource;
	}

	public void setMeDataSource(MeDataSource meDataSource) {
		this.meDataSource = meDataSource;
	}
}
