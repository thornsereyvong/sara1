package com.balancika.crm.services.impl.report;

import java.util.List;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CrmLead> reportMarketingOfLead() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CrmLead> reportLeadByMonth(String date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object reportMarketingLeadTrendsByStatus() {
		return reportDao.reportMarketingLeadTrendsByStatus();
	}

	@Override
	public List<CrmLead> reportMarketingLeadByCampaigns() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CrmLead> reportMarketingLeadByIndustry() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CrmLead> reportMarketingLeadBySource() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CrmLead> reportMarketingLeadByConverted() {
		// TODO Auto-generated method stub
		return null;
	}

}
