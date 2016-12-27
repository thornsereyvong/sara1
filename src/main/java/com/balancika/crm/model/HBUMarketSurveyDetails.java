package com.balancika.crm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity(name = "HBUMarketSurveyDetails")
@Table(name="hbu_market_survey_details")
@DynamicInsert
@DynamicUpdate
public class HBUMarketSurveyDetails implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="MS_DetailID")
	private int msDetailId;
	
	@Column(name="MarketSurveyID")
	private String marketSurveyId;
	
	@Column(name="CustID")
	private String custId;
	
	@Column(name="COM_ID")
	private String comId;
	
	@Column(name="SurveyValue")
	private short surveyValue;
	
	@Transient
	private MeDataSource meDataSource;

	public int getMsDetailId() {
		return msDetailId;
	}

	public void setMsDetailId(int msDetailId) {
		this.msDetailId = msDetailId;
	}

	public String getMarketSurveyId() {
		return marketSurveyId;
	}

	public void setMarketSurveyId(String marketSurveyId) {
		this.marketSurveyId = marketSurveyId;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getComId() {
		return comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}

	public short getSurveyValue() {
		return surveyValue;
	}

	public void setSurveyValue(short surveyValue) {
		this.surveyValue = surveyValue;
	}

	public MeDataSource getMeDataSource() {
		return meDataSource;
	}

	public void setMeDataSource(MeDataSource meDataSource) {
		this.meDataSource = meDataSource;
	}
}
