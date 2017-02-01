package com.balancika.crm.services.report;

import java.util.Map;

import com.balancika.crm.dao.report.OpportunityReportDao;
import com.balancika.crm.model.MeDataSource;

public interface OpportunityReportService {

	Map<String, Object> startupReport(MeDataSource dataSource);
	Map<String, Object> startupDateReport(String dateType, MeDataSource dataSource);
	Map<String, Object> opportunityReport(OpportunityReportDao filter);
}
