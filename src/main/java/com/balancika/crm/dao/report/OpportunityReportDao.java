package com.balancika.crm.dao.report;

import java.util.Map;

import com.balancika.crm.model.MeDataSource;

public interface OpportunityReportDao {
	Map<String, Object> startupReport(MeDataSource dataSource);
	Map<String, Object> startupDateReport(String dateType, MeDataSource dataSource);
	Map<String, Object> opportunityReport(OpportunityReportDao filter);
}
