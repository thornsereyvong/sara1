package com.balancika.crm.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.balancika.crm.dao.CrmCampaignDao;
import com.balancika.crm.model.CrmCampaign;
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
	public List<CrmCampaign> listCampaigns() {
		return dao.listCampaigns();
	}

	@Override
	public boolean updateCampaign(CrmCampaign cmp) {
		return dao.updateCampaign(cmp);	
	}

	@Override
	public String deleteCampaign(String campID) {
		return dao.deleteCampaign(campID);	
	}

	@Override
	public Object findCampaignById(String campID) {
		return dao.findCampaignById(campID);
	}

	@Override
	public boolean isCampaignNameExist(String campName) {
		return dao.isCampaignNameExist(campName);
	}

	@Override
	public List<Object> listCampaignIsNotEqual(String campID) {
		return dao.listCampaignIsNotEqual(campID);
	}

	@Override
	public CrmCampaign findCampaignDetailsById(String campID) {
		return dao.findCampaignDetailsById(campID);
	}

	@Override
	public List<Object> listCampaignParents() {
		return dao.listCampaignParents();
	}

	@Override
	public List<Object> listIdAndNameOfCompaign() {
		return dao.listIdAndNameOfCompaign();
	}

}
