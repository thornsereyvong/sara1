package com.balancika.crm.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmCampaignTypeDao;
import com.balancika.crm.model.CrmCampaignType;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.CrmCampaignTypeService;

@Service
@Transactional
public class CrmCampaignTypeServiceImpl implements CrmCampaignTypeService{

	@Autowired
	private CrmCampaignTypeDao typeDao;
	
	@Override
	public boolean addCampaignType(CrmCampaignType type) {
		return typeDao.addCampaignType(type);
	}

	@Override
	public boolean updateCampaignType(CrmCampaignType type) {
		return typeDao.updateCampaignType(type);
	}

	@Override
	public String deleteCampaignType(CrmCampaignType type) {
		return typeDao.deleteCampaignType(type);
	}

	@Override
	public List<CrmCampaignType> listAllCampaignType(MeDataSource dataSource) {
		return typeDao.listAllCampaignType(dataSource);
	}

	@Override
	public CrmCampaignType findCampaignTypeById(int typeID, MeDataSource dataSource) {
		return typeDao.findCampaignTypeById(typeID,dataSource);
	}
}
