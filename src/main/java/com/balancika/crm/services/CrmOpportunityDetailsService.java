package com.balancika.crm.services;

import java.util.List;
import java.util.Map;

import com.balancika.crm.model.CrmOpportunityDetails;

public interface CrmOpportunityDetailsService {
	boolean insertOpportunityDetails(CrmOpportunityDetails details);
	boolean updateOpportunityDetails(CrmOpportunityDetails details);
	boolean deleteOpportunityDetails(int opDetailsId);
	CrmOpportunityDetails findOpportunityDetailsById(int opDetailsId);
	List<CrmOpportunityDetails> listOpportunityDetails();
	Map<String, Object> startUpPage();
	List<CrmOpportunityDetails> listOpportunityDetailsRelatedToOpportunity(String opId);
	boolean deleteOpportunityDetails(String opId);
}
