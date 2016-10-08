package com.balancika.crm.services.report;

import java.util.List;

import com.balancika.crm.model.CrmLead;

public interface LeadReportService {
	List<CrmLead> reportLeadsConvertedAllTime();
	List<CrmLead> reportLeadConvertedByFQ(String startDate, String endDate);
	List<CrmLead> reportLeadCreatedCurrentFQ(String startDate, String endDate);
	List<CrmLead> reportMarketingOfLead();
	List<CrmLead> reportLeadByMonth(String date);
	Object reportMarketingLeadTrendsByStatus();
	List<CrmLead> reportMarketingLeadByCampaigns();
	List<CrmLead> reportMarketingLeadByIndustry();
	List<CrmLead> reportMarketingLeadBySource();
	List<CrmLead> reportMarketingLeadByConverted();
}
