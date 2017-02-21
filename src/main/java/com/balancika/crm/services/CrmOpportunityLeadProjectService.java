package com.balancika.crm.services;

import java.util.List;
import java.util.Map;

import com.balancika.crm.model.MeDataSource;

public interface CrmOpportunityLeadProjectService {
	boolean addOpportunityLeadProject(String opId, int lpId, MeDataSource dataSource);
	boolean deleteOpportunityLeadProject(String opId, int lpId, MeDataSource dataSource);
	List<Object> listOpportunityLeadProjectByOpId(String opId, MeDataSource dataSource);
	Map<String, Object> startupOpportunityLeadProject(MeDataSource dataSource);
}
