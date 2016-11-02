package com.balancika.crm.services;

import java.util.List;

import com.balancika.crm.model.CrmCampaignStatus;
import com.balancika.crm.model.MeDataSource;

public interface CrmCampaignStatusService {

	public boolean addCampaignStatus(CrmCampaignStatus status);
	public boolean updateCampaignStatus(CrmCampaignStatus status);
	public String deleteCampaignStatus(CrmCampaignStatus status);
	public List<CrmCampaignStatus> listAllCampaignStatus(MeDataSource dataSource);
	public CrmCampaignStatus findCampaignStatusById(int statusID, MeDataSource dataSource);
}
