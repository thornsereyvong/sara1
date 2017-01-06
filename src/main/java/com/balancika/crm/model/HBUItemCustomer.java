package com.balancika.crm.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity(name="HBUItemCustomer")
@Table(name="hbu_item_customer")
public class HBUItemCustomer implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ItemID")
	private String itemId;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	@JoinTable(name = "hbu_item_customer", 
		joinColumns = {@JoinColumn(name = "ItemID", referencedColumnName = "itemId")},
		inverseJoinColumns = {@JoinColumn(name="Cust_ID", referencedColumnName = "custId")})
	private List<HBUCustomer> customers;
	
	@Transient
	private MeDataSource meDataSource;

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public List<HBUCustomer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<HBUCustomer> customers) {
		this.customers = customers;
	}

	public MeDataSource getMeDataSource() {
		return meDataSource;
	}

	public void setMeDataSource(MeDataSource meDataSource) {
		this.meDataSource = meDataSource;
	}
	
}
