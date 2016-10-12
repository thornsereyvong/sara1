package com.balancika.crm.dao.report;

import java.util.List;
import java.util.Map;

import com.balancika.crm.model.CrmLead;

public interface LeadReportDao {
	List<CrmLead> reportLeadsConvertedAllTime();
	List<CrmLead> reportLeadConvertedByFQ(String startDate, String endDate);
	List<CrmLead> reportLeadCreatedCurrentFQ(String startDate, String endDate);
	List<CrmLead> reportMarketingOfLead();
	List<CrmLead> reportLeadByMonth(String date);
	List<Map<String, Object>> reportMarketingLeadTrendsByStatus();
	List<CrmLead> reportMarketingLeadByCampaigns();
	List<CrmLead> reportMarketingLeadByIndustry();
	List<CrmLead> reportMarketingLeadBySource();
	List<CrmLead> reportMarketingLeadByConverted();
}
