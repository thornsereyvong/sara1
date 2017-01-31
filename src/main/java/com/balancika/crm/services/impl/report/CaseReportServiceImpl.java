package com.balancika.crm.services.impl.report;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.report.CaseReportDao;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.model.report.CaseReport;
import com.balancika.crm.services.report.CaseReportService;

@Service
@Transactional
public class CaseReportServiceImpl implements CaseReportService{

	@Autowired
	private CaseReportDao reportDao;
	
	@Override
	public Map<String, Object> caseReportStartup(MeDataSource dataSource) {
		return reportDao.caseReportStartup(dataSource);
	}

	@Override
	public List<Map<String, Object>> caseReport(CaseReport report) {
		return reportDao.caseReport(report);
	}

}
