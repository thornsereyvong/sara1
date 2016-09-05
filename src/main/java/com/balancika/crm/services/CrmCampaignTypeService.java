package com.balancika.crm.services;

import java.util.List;

import com.balancika.crm.model.CrmCampaignType;

public interface CrmCampaignTypeService {

	public boolean addCampaignType(CrmCampaignType type);
	public boolean updateCampaignType(CrmCampaignType type);
	public String deleteCampaignType(int typeID);
	public List<CrmCampaignType> listAllCampaignType();
	public CrmCampaignType findCampaignTypeById(int typeID);
}
