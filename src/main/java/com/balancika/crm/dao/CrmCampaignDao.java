package com.balancika.crm.dao;

import java.util.List;

import com.balancika.crm.model.CrmCampaign;
import com.balancika.crm.model.CrmOpportunity;

public interface CrmCampaignDao {
	
	public boolean insertCampaign(CrmCampaign cmp);
	public List<CrmCampaign> listCampaigns();
	public boolean updateCampaign(CrmCampaign cmp);
	public String deleteCampaign(String campID);
	Object findCampaignById(String campID);
	CrmCampaign findCampaignDetailsById(String campID);
	boolean isCampaignNameExist(String campName);
	List<Object> listCampaignIsNotEqual(String campID);
	List<Object> listCampaignParents();
	List<Object> listIdAndNameOfCompaign();
	List<CrmOpportunity> getOpportunitiesRelatedToCampaign(String campID);
}
