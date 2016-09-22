package com.balancika.crm.dao;

import java.util.List;
import java.util.Map;

import com.balancika.crm.model.CrmOpportunity;

public interface CrmOpportunityDao {

	boolean isInsertOpportunity(CrmOpportunity opportunity);
	boolean isUpdateOpportunity(CrmOpportunity opportunity);
	boolean isDeleteOpportunity(String opId);
	CrmOpportunity findOpportunityDetailsById(String opId);
	Object findOpportunityById(String opId);
	List<CrmOpportunity> listOpportunities();
	List<Object> listOpportunitiesWithSpecificUser(String username);
	Map<String, Object> listInformationRelateToOpportunity(String opId);
	List<Object> listContactsRelatedToOpportuntiy(String opId);
	List<Object> listQuotationsRelatedToOpportuntiy(String opId);
	List<Object> listSaleOrdersRelatedToOpportuntiy(String opId);
}
