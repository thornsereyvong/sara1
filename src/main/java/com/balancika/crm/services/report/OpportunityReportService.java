package com.balancika.crm.services.report;

import java.util.List;
import java.util.Map;

import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.model.report.OpportunityReport;

public interface OpportunityReportService {

	Map<String, Object> startupReport(MeDataSource dataSource);
	Map<String, Object> startupDateReport(String dateType, MeDataSource dataSource);
	List<Map<String, Object>> opportunityReport(OpportunityReport filter);
}
