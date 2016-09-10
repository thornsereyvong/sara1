package com.balancika.crm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblpricecode")
public class PriceCode implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="PriceCode")
	private String priceCode;
	
	@Column(name="Description")
	private String des;
	
	@Column(name = "ReportPrice")
	private int reportPrice;
	
	@Column(name="DefaultPrice")
	private int defaultPrice;
	
	@Column(name="SpecialPrice")
	private int specialPrice;

	public String getPriceCode() {
		return priceCode;
	}

	public void setPriceCode(String priceCode) {
		this.priceCode = priceCode;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public int getReportPrice() {
		return reportPrice;
	}

	public void setReportPrice(int reportPrice) {
		this.reportPrice = reportPrice;
	}

	public int getDefaultPrice() {
		return defaultPrice;
	}

	public void setDefaultPrice(int defaultPrice) {
		this.defaultPrice = defaultPrice;
	}

	public int getSpecialPrice() {
		return specialPrice;
	}

	public void setSpecialPrice(int specialPrice) {
		this.specialPrice = specialPrice;
	}
}
