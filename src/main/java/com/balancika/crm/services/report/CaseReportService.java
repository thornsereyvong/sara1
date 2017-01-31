package com.balancika.crm.services.report;

import java.util.List;
import java.util.Map;

import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.model.report.CaseReport;

public interface CaseReportService {
	Map<String, Object> caseReportStartup(MeDataSource dataSource);
	List<Map<String, Object>> caseReport(CaseReport report);
}
