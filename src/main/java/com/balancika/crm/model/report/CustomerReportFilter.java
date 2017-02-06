package com.balancika.crm.model.report;

import java.util.Date;

import org.hibernate.annotations.Type;

import com.balancika.crm.model.MeDataSource;

public class CustomerReportFilter {
	
	private String dateType;
	
	@Type(type="date")
	private Date startDate;
	
	@Type(type="date")
	private Date endDate;
	private MeDataSource dataSource;
	
	public String getDateType() {
		return dateType;
	}
	public void setDateType(String dateType) {
		this.dateType = dateType;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public MeDataSource getDataSource() {
		return dataSource;
	}
	public void setDataSource(MeDataSource dataSource) {
		this.dataSource = dataSource;
	}
}
