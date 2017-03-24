package com.balancika.crm.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.balancika.crm.dao.CrmCampaignDao;
import com.balancika.crm.model.CrmCampaign;
import com.balancika.crm.model.CrmOpportunity;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.CrmCampaignService;

@Service
@Transactional
public class CrmCampaignServiceImpl implements CrmCampaignService{
	
	@Autowired
	private CrmCampaignDao dao;

	@Override
	public boolean insertCampaign(CrmCampaign cmp) {
		return dao.insertCampaign(cmp);
	}

	@Override
	public List<CrmCampaign> listCampaigns(MeDataSource dataSource) {
		return dao.listCampaigns(dataSource);
	}

	@Override
	public boolean updateCampaign(CrmCampaign cmp) {
		return dao.updateCampaign(cmp);	
	}

	@Override
	public String deleteCampaign(String campID, MeDataSource dataSource) {
		return dao.deleteCampaign(campID, dataSource);	
	}

	@Override
	public Object findCampaignById(String campID, MeDataSource dataSource) {
		return dao.findCampaignById(campID, dataSource);
	}

	@Override
	public boolean isCampaignNameExist(String campName, MeDataSource dataSource) {
		return dao.isCampaignNameExist(campName, dataSource);
	}

	@Override
	public List<Object> listCampaignIsNotEqual(String campID, MeDataSource dataSource) {
		return dao.listCampaignIsNotEqual(campID, dataSource);
	}

	@Override
	public CrmCampaign findCampaignDetailsById(String campID, MeDataSource dataSource) {
		return dao.findCampaignDetailsById(campID, dataSource);
	}

	@Override
	public List<Object> listCampaignParents(MeDataSource dataSource) {
		return dao.listCampaignParents(dataSource);
	}

	@Override
	public List<Object> listIdAndNameOfCompaign(MeDataSource dataSource) {
		return dao.listIdAndNameOfCompaign(dataSource);
	}

	@Override
	public List<CrmOpportunity> getOpportunitiesRelatedToCampaign(String campID, MeDataSource dataSource) {
		return dao.getOpportunitiesRelatedToCampaign(campID, dataSource);
	}

	@Override
	public List<Object> getLeadRelateToCampaign(String camId, MeDataSource dataSource) {		
		return dao.getLeadRelateToCampaign(camId, dataSource);
	}
}
