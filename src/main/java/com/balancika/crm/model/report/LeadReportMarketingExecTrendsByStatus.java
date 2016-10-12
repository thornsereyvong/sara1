package com.balancika.crm.model.report;

public class LeadReportMarketingExecTrendsByStatus {

	private String statusName;
	private int unitCount;
	//private String leadCreatedDate;
	
	public LeadReportMarketingExecTrendsByStatus(String statusName, int unitCount){
		this.statusName = statusName;
		this.unitCount = unitCount;
	}
	
	public String getStatusName() {
		return statusName;
	}
	
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
	public int getUnitCount() {
		return unitCount;
	}
	
	public void setUnitCount(int unitCount) {
		this.unitCount = unitCount;
	}
	
	/*public String getLeadCreatedDate() {
		return leadCreatedDate;
	}
	public void setLeadCreatedDate(String leadCreatedDate) {
		this.leadCreatedDate = leadCreatedDate;
	}*/
	
}
