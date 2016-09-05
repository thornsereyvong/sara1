package com.balancika.crm.services.impl;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmCampaignStatusDao;
import com.balancika.crm.model.CrmCampaignStatus;
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
	public String deleteCampaignStatus(int statusID) {
		return statusDao.deleteCampaignStatus(statusID);
	}

	@Override
	public List<CrmCampaignStatus> listAllCampaignStatus() {
		return statusDao.listAllCampaignStatus();
	}

	@Override
	public CrmCampaignStatus findCampaignStatusById(int statusID) {
		return statusDao.findCampaignStatusById(statusID);
	}
}
