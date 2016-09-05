package com.balancika.crm.services;

import java.util.List;

import com.balancika.crm.model.CrmOpportunity;

public interface CrmOpportunityService {
	boolean isInsertOpportunity(CrmOpportunity opportunity);
	boolean isUpdateOpportunity(CrmOpportunity opportunity);
	boolean isDeleteOpportunity(String opId);
	CrmOpportunity findOpportunityDetailsById(String opId);
	Object findOpportunityById(String opId);
	List<CrmOpportunity> listOpportunities();
}
