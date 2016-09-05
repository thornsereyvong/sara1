package com.balancika.crm.services;

import java.util.List;

import com.balancika.crm.model.CrmCampaignStatus;

public interface CrmCampaignStatusService {

	public boolean addCampaignStatus(CrmCampaignStatus status);
	public boolean updateCampaignStatus(CrmCampaignStatus status);
	public String deleteCampaignStatus(int statusID);
	public List<CrmCampaignStatus> listAllCampaignStatus();
	public CrmCampaignStatus findCampaignStatusById(int statusID);
}
