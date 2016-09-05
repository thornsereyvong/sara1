package com.balancika.crm.dao;

import java.util.List;

import com.balancika.crm.model.CrmCampaignType;

public interface CrmCampaignTypeDao {

	public boolean addCampaignType(CrmCampaignType type);
	public boolean updateCampaignType(CrmCampaignType type);
	public String deleteCampaignType(int typeID);
	public List<CrmCampaignType> listAllCampaignType();
	public CrmCampaignType findCampaignTypeById(int typeID);
}
