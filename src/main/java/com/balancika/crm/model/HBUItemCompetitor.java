package com.balancika.crm.model;

import java.io.Serializable;
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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity(name="HBUItemCompetitor")
@Table(name="tblitem")
public class HBUItemCompetitor implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ItemID")
	private String itemId;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@Fetch(FetchMode.JOIN)
	@JoinTable(name = "hbu_competitor_item", 
		joinColumns = {@JoinColumn(name = "ItemID")},
		inverseJoinColumns = {@JoinColumn(name="COM_ID")})
	private List<HBUCompetitor> competitors;
	
	@Transient
	private MeDataSource meDataSource;
 
	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public List<HBUCompetitor> getCompetitors() {
		return competitors;
	}

	public void setCompetitors(List<HBUCompetitor> competitors) {
		this.competitors = competitors;
	}

	public MeDataSource getMeDataSource() {
		return meDataSource;
	}

	public void setMeDataSource(MeDataSource meDataSource) {
		this.meDataSource = meDataSource;
	}

}
