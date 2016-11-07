package com.balancika.crm.services.report;

import java.util.List;
import java.util.Map;

import com.balancika.crm.model.CrmLead;
import com.balancika.crm.model.MeDataSource;

public interface LeadReportService {
	List<CrmLead> reportLeadsConvertedAllTime(MeDataSource dataSource);
	List<CrmLead> reportLeadConvertedByFQ(String startDate, String endDate, MeDataSource dataSource);
	List<CrmLead> reportLeadCreatedCurrentFQ(String startDate, String endDate, MeDataSource dataSource);
	List<CrmLead> reportMarketingOfLead(MeDataSource dataSource);
	List<CrmLead> reportLeadByMonth(String date, MeDataSource dataSource);
	List<Map<String, Object>> reportMarketingLeadTrendsByStatus(MeDataSource dataSource);
	List<CrmLead> reportMarketingLeadByCampaigns(MeDataSource dataSource);
	List<CrmLead> reportMarketingLeadByIndustry(MeDataSource dataSource);
	List<CrmLead> reportMarketingLeadBySource(MeDataSource dataSource);
	List<CrmLead> reportMarketingLeadByConverted(MeDataSource dataSource);
}
