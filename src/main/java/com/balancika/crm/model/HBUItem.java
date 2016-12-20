package com.balancika.crm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity(name="HBUItem")
@Table(name="tblitem")
@DynamicInsert
@DynamicUpdate
public class HBUItem implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ItemID")
	private String itemId;
	
	@Column(name="ItemName")
	private String itemName;
	
	@Transient
	private MeDataSource meDataSource;

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

	public MeDataSource getMeDataSource() {
		return meDataSource;
	}

	public void setMeDataSource(MeDataSource meDataSource) {
		this.meDataSource = meDataSource;
	}
}
