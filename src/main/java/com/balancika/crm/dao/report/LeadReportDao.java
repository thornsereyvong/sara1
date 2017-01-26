package com.balancika.crm.dao.report;

import java.util.List;
import java.util.Map;

import com.balancika.crm.model.CrmLead;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.model.report.LeadReport;

public interface LeadReportDao {
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
	List<Map<String, Object>> reportLead(LeadReport leadReport);
	Map<String, Object> startupReportLead(MeDataSource dataSource);
	List<Map<String, Object>> startupDate(String dateType, MeDataSource dataSource);
}
