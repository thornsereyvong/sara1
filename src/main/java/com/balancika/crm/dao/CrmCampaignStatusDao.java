package com.balancika.crm.dao;

import java.util.List;

import com.balancika.crm.model.CrmCampaignStatus;
import com.balancika.crm.model.MeDataSource;

public interface CrmCampaignStatusDao {
	
	public boolean addCampaignStatus(CrmCampaignStatus status);
	public boolean updateCampaignStatus(CrmCampaignStatus status);
	public String deleteCampaignStatus(CrmCampaignStatus status);
	public List<CrmCampaignStatus> listAllCampaignStatus(MeDataSource dataSource);
	public CrmCampaignStatus findCampaignStatusById(int statusID, MeDataSource dataSource);

}
