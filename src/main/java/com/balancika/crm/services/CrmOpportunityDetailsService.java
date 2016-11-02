package com.balancika.crm.services;

import java.util.List;
import java.util.Map;

import com.balancika.crm.model.CrmOpportunityDetails;
import com.balancika.crm.model.MeDataSource;

public interface CrmOpportunityDetailsService {
	boolean insertOpportunityDetails(CrmOpportunityDetails details);
	boolean updateOpportunityDetails(CrmOpportunityDetails details);
	boolean deleteOpportunityDetails(CrmOpportunityDetails details);
	CrmOpportunityDetails findOpportunityDetailsById(int opDetailsId, MeDataSource dataSource);
	List<CrmOpportunityDetails> listOpportunityDetails(MeDataSource dataSource);
	Map<String, Object> startUpPage(MeDataSource dataSource);
	List<CrmOpportunityDetails> listOpportunityDetailsRelatedToOpportunity(String opId, MeDataSource dataSource);
	boolean deleteOpportunityDetails(String opId, MeDataSource dataSource);
}
