package com.balancika.crm.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


@Entity
@Table(name="crm_opportunity_detail")
@DynamicUpdate(value = true)
@DynamicInsert(value = true)
public class CrmOpportunityDetails implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="OP_D_ID")
	private int opDetailsId;
	
	@Column(name="OP_ID")
	private String opId;
	
	@Column(name="LineNo")
	private int lineNo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="ItemID")
	private AmeItem item;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="UomID")
	private AmeUom uom;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="LocationID")
	private AmeLocation location;
	
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
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="ClassID")
	private AmeClass ameClass;
	
	@Column(name="DisInv", columnDefinition = "double default 0.00")
	private double disInv;
	
	@Transient
	private List<Object> items;
	
	@Transient
	private List<Object> locations;
	
	@Transient
	private Object opportunity;
	
	@Transient
	private MeDataSource meDataSource;
	
	public final MeDataSource getMeDataSource() {
		return meDataSource;
	}

	public final void setMeDataSource(MeDataSource meDataSource) {
		this.meDataSource = meDataSource;
	}

	public int getOpDetailsId() {
		return opDetailsId;
	}

	public void setOpDetailsId(int opDetailsId) {
		this.opDetailsId = opDetailsId;
	}


	public int getLineNo() {
		return lineNo;
	}

	public void setLineNo(int lineNo) {
		this.lineNo = lineNo;
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

	public double getDisInv() {
		return disInv;
	}

	public void setDisInv(double disInv) {
		this.disInv = disInv;
	}

	public String getOpId() {
		return opId;
	}

	public void setOpId(String opId) {
		this.opId = opId;
	}

	public List<Object> getItems() {
		return items;
	}

	public void setItems(List<Object> items) {
		this.items = items;
	}

	public List<Object> getLocations() {
		return locations;
	}

	public void setLocations(List<Object> locations) {
		this.locations = locations;
	}

	public Object getOpportunity() {
		return opportunity;
	}

	public void setOpportunity(Object opportunity) {
		this.opportunity = opportunity;
	}

	public AmeItem getItem() {
		return item;
	}

	public void setItem(AmeItem item) {
		this.item = item;
	}

	public AmeUom getUom() {
		return uom;
	}

	public void setUom(AmeUom uom) {
		this.uom = uom;
	}

	public AmeLocation getLocation() {
		return location;
	}

	public void setLocation(AmeLocation location) {
		this.location = location;
	}

	public AmeClass getAmeClass() {
		return ameClass;
	}

	public void setAmeClass(AmeClass ameClass) {
		this.ameClass = ameClass;
	}
	
}
