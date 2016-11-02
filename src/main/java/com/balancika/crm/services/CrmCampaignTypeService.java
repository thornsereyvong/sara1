package com.balancika.crm.services;

import java.util.List;

import com.balancika.crm.model.CrmCampaignType;
import com.balancika.crm.model.MeDataSource;

public interface CrmCampaignTypeService {

	public boolean addCampaignType(CrmCampaignType type);
	public boolean updateCampaignType(CrmCampaignType type);
	public String deleteCampaignType(CrmCampaignType type);
	public List<CrmCampaignType> listAllCampaignType(MeDataSource dataSource);
	public CrmCampaignType findCampaignTypeById(int typeID, MeDataSource dataSource);
}
