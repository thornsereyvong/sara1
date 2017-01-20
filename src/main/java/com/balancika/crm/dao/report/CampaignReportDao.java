package com.balancika.crm.dao.report;

import java.util.List;
import java.util.Map;

import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.model.report.CampaingReport;

public interface CampaignReportDao {
	Map<String, Object> campaignReportStartup(String userId, MeDataSource dataSource);
	List<Map<String, Object>> reportTopCampaign(CampaingReport campaingReport);
	List<Map<String, Object>> reportLeadByCampaing(MeDataSource dataSource);
}
