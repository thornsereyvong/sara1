package com.balancika.crm.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;

@Entity(name="HBUMarketSurvey")
@Table(name = "hbu_market_survey")
@DynamicInsert
@DynamicUpdate
public class HBUMarketSurvey implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "MS_ID")
	private String msId;
	
	@Type(type = "date")
	@Column(name="MS_Date", updatable = false)
	private Date msDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "MS_ItemID")
	private HBUItem item;
	
	@Column(name="MS_CreateBy", updatable = false)
	private String msCreateBy;
	
	@Column(name="MS_ModifiedBy")
	private String msModifiedBy;
	
	@Column(name="MS_ModifiedDate")
	private LocalDateTime msModifiedDate;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="MarketSurveyID")
	private List<HBUMarketSurveyDetails> details;
	
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

	public Date getMsDate() {
		return msDate;
	}

	public void setMsDate(Date msDate) {
		this.msDate = msDate;
	}

	public HBUItem getItem() {
		return item;
	}

	public void setItem(HBUItem item) {
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

	public List<HBUMarketSurveyDetails> getDetails() {
		return details;
	}

	public void setDetails(List<HBUMarketSurveyDetails> details) {
		this.details = details;
	}
}
