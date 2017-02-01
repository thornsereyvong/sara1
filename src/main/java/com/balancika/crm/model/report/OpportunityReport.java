package com.balancika.crm.model.report;

import com.balancika.crm.model.MeDataSource;

public class OpportunityReport {

	private String dateType;
	private String stage;
	private String type;
	private String startDate;
	private String endDate;
	private String campaign;
	private String customer;
	private String source;
	private String assignTo;
	private MeDataSource dataSource;
	
	public String getDateType() {
		return dateType;
	}
	
	public void setDateType(String dateType) {
		this.dateType = dateType;
	}
	
	public String getStage() {
		return stage;
	}
	
	public void setStage(String stage) {
		this.stage = stage;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getStartDate() {
		return startDate;
	}
	
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	public String getEndDate() {
		return endDate;
	}
	
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public String getCampaign() {
		return campaign;
	}
	
	public void setCampaign(String campaign) {
		this.campaign = campaign;
	}
	
	public String getCustomer() {
		return customer;
	}
	
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	
	public String getSource() {
		return source;
	}
	
	public void setSource(String source) {
		this.source = source;
	}
	
	public String getAssignTo() {
		return assignTo;
	}
	
	public void setAssignTo(String assignTo) {
		this.assignTo = assignTo;
	}
	
	public MeDataSource getDataSource() {
		return dataSource;
	}
	
	public void setDataSource(MeDataSource dataSource) {
		this.dataSource = dataSource;
	}
}
