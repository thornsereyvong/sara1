package com.balancika.crm.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.balancika.crm.utilities.LocalDateTimePersistenceConverter;

@Entity
@Table(name = "hbu_market_survey")
@DynamicInsert
@DynamicUpdate
public class HBUMarketSurvey implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "MS_ID")
	private String msId;
	
	@Convert(converter = LocalDateTimePersistenceConverter.class)
	@Column(name="MS_Date")
	private LocalDateTime msDate;
	
	@Transient
	private String convertMsDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "MS_ItemID")
	private AmeItem item;
	
	@Column(name="MS_CreateBy")
	private String msCreateBy;
	
	@Column(name="MS_ModifiedBy")
	private String msModifiedBy;
	
	@Column(name="MS_ModifiedDate")
	private LocalDateTime msModifiedDate;
	
	@Transient
	private String convertModifiedDate;
	
	@Transient
	private MeDataSource meDataSource;

	public String getMsId() {
		return msId;
	}

	public void setMsId(String msId) {
		this.msId = msId;
	}

	public LocalDateTime getMsDate() {
		return msDate;
	}

	public void setMsDate(LocalDateTime msDate) {
		this.msDate = msDate;
	}

	public String getConvertMsDate() {
		return convertMsDate;
	}

	public void setConvertMsDate(String convertMsDate) {
		this.convertMsDate = convertMsDate;
	}

	public AmeItem getItem() {
		return item;
	}

	public void setItem(AmeItem item) {
		this.item = item;
	}

	public String getMsCreateBy() {
		return msCreateBy;
	}

	public void setMsCreateBy(String msCreateBy) {
		this.msCreateBy = msCreateBy;
	}

	public String getMsModifiedBy() {
		return msModifiedBy;
	}

	public void setMsModifiedBy(String msModifiedBy) {
		this.msModifiedBy = msModifiedBy;
	}

	public LocalDateTime getMsModifiedDate() {
		return msModifiedDate;
	}

	public void setMsModifiedDate(LocalDateTime msModifiedDate) {
		this.msModifiedDate = msModifiedDate;
	}

	public String getConvertModifiedDate() {
		return convertModifiedDate;
	}

	public void setConvertModifiedDate(String convertModifiedDate) {
		this.convertModifiedDate = convertModifiedDate;
	}

	public MeDataSource getMeDataSource() {
		return meDataSource;
	}

	public void setMeDataSource(MeDataSource meDataSource) {
		this.meDataSource = meDataSource;
	}
	
}
