package com.balancika.crm.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
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

import com.balancika.crm.utilities.LocalDateTimePersistenceConverter;

@Entity
@Table(name = "hbu_competitor")
@DynamicInsert
@DynamicUpdate
public class HBUCompetitor implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "COM_ID", nullable = false)
	private String comId;
	
	@Column(name = "COM_Name")
	private String comName;
	
	@Column(name = "COM_Address")
	private String comAddress;
	
	@Convert(converter = LocalDateTimePersistenceConverter.class)
	@Column(name = "COM_CreateDate")
	private LocalDateTime comCreateDate;
	
	@Column(name = "COM_CreateBy")
	private String comCreateBy;
	
	@Convert(converter = LocalDateTimePersistenceConverter.class)
	@Column(name="COM_ModifiedDate")
	private LocalDateTime comModifiedDate;
	
	@Column(name="COM_ModifiedBy")
	private String comModifiedBy;
	
	@Transient
	private MeDataSource meDataSource;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="hbu_competitor_item",
			joinColumns = {@JoinColumn(name="COM_ID", nullable = false)},
			inverseJoinColumns = {@JoinColumn(name="ItemID", nullable = false)}
			)
	private List<HBUItem> items = new ArrayList<HBUItem>();

	public String getComId() {
		return comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}

	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

	public String getComAddress() {
		return comAddress;
	}

	public void setComAddress(String comAddress) {
		this.comAddress = comAddress;
	}

	public LocalDateTime getComCreateDate() {
		return comCreateDate;
	}

	public void setComCreateDate(LocalDateTime comCreateDate) {
		this.comCreateDate = comCreateDate;
	}

	public String getComCreateBy() {
		return comCreateBy;
	}

	public void setComCreateBy(String comCreateBy) {
		this.comCreateBy = comCreateBy;
	}

	public LocalDateTime getComModifiedDate() {
		return comModifiedDate;
	}

	public void setComModifiedDate(LocalDateTime comModifiedDate) {
		this.comModifiedDate = comModifiedDate;
	}

	public String getComModifiedBy() {
		return comModifiedBy;
	}

	public void setComModifiedBy(String comModifiedBy) {
		this.comModifiedBy = comModifiedBy;
	}

	public MeDataSource getMeDataSource() {
		return meDataSource;
	}

	public void setMeDataSource(MeDataSource meDataSource) {
		this.meDataSource = meDataSource;
	}

	public List<HBUItem> getItems() {
		return items;
	}

	public void setItems(List<HBUItem> items) {
		this.items = items;
	}
}
