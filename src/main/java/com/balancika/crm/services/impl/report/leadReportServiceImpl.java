package com.balancika.crm.services.impl.report;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.report.LeadReportDao;
import com.balancika.crm.model.CrmLead;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.model.report.LeadReport;
import com.balancika.crm.services.report.LeadReportService;

@Service
@Transactional
public class leadReportServiceImpl implements LeadReportService{
	
	@Autowired
	private LeadReportDao reportDao;

	@Override
	public List<CrmLead> reportLeadsConvertedAllTime(MeDataSource dataSource) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CrmLead> reportLeadConvertedByFQ(String startDate, String endDate, MeDataSource dataSource) {
		return reportDao.reportLeadConvertedByFQ(startDate, endDate, dataSource);
	}

	@Override
	public List<CrmLead> reportLeadCreatedCurrentFQ(String startDate, String endDate, MeDataSource dataSource) {
		return reportDao.reportLeadCreatedCurrentFQ(startDate, endDate, dataSource);
	}

	@Override
	public List<CrmLead> reportMarketingOfLead(MeDataSource dataSource) {
		return reportDao.reportMarketingOfLead(dataSource);
	}

	@Override
	public List<CrmLead> reportLeadByMonth(String date, MeDataSource dataSource) {
		return reportDao.reportLeadByMonth(date, dataSource);
	}

	@Override
	public List<Map<String, Object>> reportMarketingLeadTrendsByStatus(MeDataSource dataSource) {
		return reportDao.reportMarketingLeadTrendsByStatus(dataSource);
	}

	@Override
	public List<CrmLead> reportMarketingLeadByCampaigns(MeDataSource dataSource) {
		return reportDao.reportMarketingLeadByCampaigns(dataSource);
	}

	@Override
	public List<CrmLead> reportMarketingLeadByIndustry(MeDataSource dataSource) {
		return reportDao.reportMarketingLeadByIndustry(dataSource);
	}

	@Override
	public List<CrmLead> reportMarketingLeadBySource(MeDataSource dataSource) {
		return reportDao.reportMarketingLeadBySource(dataSource);
	}

	@Override
	public List<CrmLead> reportMarketingLeadByConverted(MeDataSource dataSource) {
		return reportDao.reportMarketingLeadByConverted(dataSource);
	}

	@Override
	public List<Map<String, Object>> reportLead(LeadReport leadReport) {
		return reportDao.reportLead(leadReport);	
	}

	@Override
	public Map<String, Object> startupReportLead(MeDataSource dataSource) {
		return reportDao.startupReportLead(dataSource);
	}

	@Override
	public Map<String, Object> startupDate(String dateType, MeDataSource dataSource) {
		return reportDao.startupDate(dateType, dataSource);
	}

}
