package com.balancika.crm.dao;

import java.util.List;

import com.balancika.crm.model.CrmCampaign;
import com.balancika.crm.model.CrmLead;
import com.balancika.crm.model.CrmOpportunity;
import com.balancika.crm.model.MeDataSource;

public interface CrmCampaignDao {
	public boolean insertCampaign(CrmCampaign cmp);
	public List<CrmCampaign> listCampaigns(MeDataSource dataSource);
	public boolean updateCampaign(CrmCampaign cmp);
	public String deleteCampaign(String campID, MeDataSource dataSource);
	Object findCampaignById(String campID, MeDataSource dataSource);
	CrmCampaign findCampaignDetailsById(String campID, MeDataSource dataSource);
	boolean isCampaignNameExist(String campName, MeDataSource dataSource);
	List<Object> listCampaignIsNotEqual(String campID, MeDataSource dataSource);
	List<Object> listCampaignParents(MeDataSource dataSource);
	List<Object> listIdAndNameOfCompaign(MeDataSource dataSource);
	List<CrmOpportunity> getOpportunitiesRelatedToCampaign(String campID, MeDataSource dataSource);
	List<Object> getLeadRelateToCampaign(String camId, MeDataSource dataSource);
}
