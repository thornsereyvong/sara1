package com.balancika.crm.services.impl.report;

import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.report.OpportunityReportDao;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.report.OpportunityReportService;

@Service
@Transactional
public class OpportunityReportServiceImpl implements OpportunityReportService{
	
	@Autowired
	private OpportunityReportDao reportDao;

	@Override
	public Map<String, Object> startupReport(MeDataSource dataSource) {
		return reportDao.startupReport(dataSource);
	}

	@Override
	public Map<String, Object> startupDateReport(String dateType, MeDataSource dataSource) {
		return reportDao.startupDateReport(dateType, dataSource);
	}

	@Override
	public Map<String, Object> opportunityReport(OpportunityReportDao filter) {
		return reportDao.opportunityReport(filter);
	}

}
