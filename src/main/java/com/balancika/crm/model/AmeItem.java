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

@Entity(name = "AmeItem")
@Table(name="tblitem")
public class AmeItem implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ItemID")
	private String itemId;
	
	@Column(name="ItemName")
	private String itemName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="UOMID")
	private AmeUom itemUom;
	
	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public AmeUom getItemUom() {
		return itemUom;
	}

	public void setItemUom(AmeUom itemUom) {
		this.itemUom = itemUom;
	}
}
