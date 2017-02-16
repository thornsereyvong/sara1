package com.balancika.crm.services.impl.report;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.balancika.crm.dao.report.CampaignReportDao;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.model.report.CampaignReportModel;
import com.balancika.crm.model.report.CampaingReport;
import com.balancika.crm.services.report.CampaignReportSevice;

@Service
@Transactional
public class CampaignReportServiceImpl implements CampaignReportSevice{

	@Autowired
	private CampaignReportDao campaignReportDao;
	
	@Override
	public Map<String, Object> campaignReportStartup(String userId, MeDataSource dataSource) {
		return campaignReportDao.campaignReportStartup(userId, dataSource);
	}

	@Override
	public List<Map<String, Object>> reportTopCampaign(CampaingReport campaingReport) {
		return campaignReportDao.reportTopCampaign(campaingReport);
	}

	@Override
	public List<CampaignReportModel> reportLeadByCampaing(MeDataSource dataSource) {
		return campaignReportDao.reportLeadByCampaing(dataSource);
	}

}
