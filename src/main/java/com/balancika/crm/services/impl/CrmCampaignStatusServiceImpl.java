package com.balancika.crm.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmCampaignStatusDao;
import com.balancika.crm.model.CrmCampaignStatus;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.CrmCampaignStatusService;

@Service
@Transactional
public class CrmCampaignStatusServiceImpl implements CrmCampaignStatusService {
	
	@Autowired
	private CrmCampaignStatusDao statusDao;

	@Override
	public boolean addCampaignStatus(CrmCampaignStatus status) {
		return statusDao.addCampaignStatus(status);
	}

	@Override
	public boolean updateCampaignStatus(CrmCampaignStatus status) {
		return statusDao.updateCampaignStatus(status);
	}

	@Override
	public String deleteCampaignStatus(CrmCampaignStatus status) {
		return statusDao.deleteCampaignStatus(status);
	}

	@Override
	public List<CrmCampaignStatus> listAllCampaignStatus(MeDataSource dataSource) {
		return statusDao.listAllCampaignStatus(dataSource);
	}

	@Override
	public CrmCampaignStatus findCampaignStatusById(int statusID, MeDataSource dataSource) {
		return statusDao.findCampaignStatusById(statusID, dataSource);
	}
}
