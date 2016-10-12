package com.balancika.crm.services.impl.report;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.report.LeadReportDao;
import com.balancika.crm.model.CrmLead;
import com.balancika.crm.services.report.LeadReportService;

@Service
@Transactional
public class leadReportServiceImpl implements LeadReportService{
	
	@Autowired
	private LeadReportDao reportDao;

	@Override
	public List<CrmLead> reportLeadsConvertedAllTime() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CrmLead> reportLeadConvertedByFQ(String startDate, String endDate) {
		return reportDao.reportLeadConvertedByFQ(startDate, endDate);
	}

	@Override
	public List<CrmLead> reportLeadCreatedCurrentFQ(String startDate, String endDate) {
		return reportDao.reportLeadCreatedCurrentFQ(startDate, endDate);
	}

	@Override
	public List<CrmLead> reportMarketingOfLead() {
		return reportDao.reportMarketingOfLead();
	}

	@Override
	public List<CrmLead> reportLeadByMonth(String date) {
		return reportDao.reportLeadByMonth(date);
	}

	@Override
	public List<Map<String, Object>> reportMarketingLeadTrendsByStatus() {
		return reportDao.reportMarketingLeadTrendsByStatus();
	}

	@Override
	public List<CrmLead> reportMarketingLeadByCampaigns() {
		return reportDao.reportMarketingLeadByCampaigns();
	}

	@Override
	public List<CrmLead> reportMarketingLeadByIndustry() {
		return reportDao.reportMarketingLeadByIndustry();
	}

	@Override
	public List<CrmLead> reportMarketingLeadBySource() {
		return reportDao.reportMarketingLeadBySource();
	}

	@Override
	public List<CrmLead> reportMarketingLeadByConverted() {
		return reportDao.reportMarketingLeadByConverted();
	}

}
