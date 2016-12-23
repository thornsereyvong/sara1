package com.balancika.crm.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	
	@Column(name="ItemName", updatable = false)
	private String itemName;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="hbu_competitor_item",
			joinColumns = {@JoinColumn(name="ItemID", nullable = false)},
			inverseJoinColumns = {@JoinColumn(name="COM_ID", nullable = false)}
			)
	private List<HBUCompetitor> competitors = new ArrayList<HBUCompetitor>();
	
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

	public List<HBUCompetitor> getCompetitors() {
		return competitors;
	}

	public void setCompetitors(List<HBUCompetitor> competitors) {
		this.competitors = competitors;
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
