package com.balancika.crm.services;

import java.util.List;

import com.balancika.crm.model.CrmCampaign;

public interface CrmCampaignService {

	public boolean insertCampaign(CrmCampaign cmp);
	public List<CrmCampaign> listCampaigns();
	public boolean updateCampaign(CrmCampaign cmp);
	public String deleteCampaign(String campID);
	Object findCampaignById(String campID);
	CrmCampaign findCampaignDetailsById(String campID);
	boolean isCampaignNameExist(String campName);
	List<CrmCampaign> listCampaignIsNotEqual(String campID);
	List<Object> listCampaignParents();
	List<Object> listIdAndNameOfCompaign();
}
