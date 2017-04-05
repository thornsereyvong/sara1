package com.balancika.crm.dao;

import java.util.List;
import java.util.Map;

import com.balancika.crm.model.CrmOpportunity;
import com.balancika.crm.model.MeDataSource;

public interface CrmOpportunityDao {

	boolean isInsertOpportunity(CrmOpportunity opportunity);
	boolean isUpdateOpportunity(CrmOpportunity opportunity);
	boolean isDeleteOpportunity(CrmOpportunity opportunity);
	CrmOpportunity findOpportunityDetailsById(String opId, MeDataSource dataSource);
	Object findOpportunityById(String opId, MeDataSource dataSource);
	List<CrmOpportunity> listOpportunities(MeDataSource dataSource);
	List<Object> listOpportunitiesWithSpecificUser(String username, MeDataSource dataSource);
	List<Object> listContactsRelatedToOpportuntiy(String opId, MeDataSource dataSource);
	List<Object> listQuotationsRelatedToOpportuntiy(String opId, MeDataSource dataSource);
	List<Object> listSaleOrdersRelatedToOpportuntiy(String opId, MeDataSource dataSource);
	boolean updateCustomFieldsOfOpprotunity(CrmOpportunity opp);
	Map<String, Object> viewOpportunityById(String opId, String userId, MeDataSource dataSource);
}
