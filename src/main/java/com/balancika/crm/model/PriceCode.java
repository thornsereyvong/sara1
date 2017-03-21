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
}
