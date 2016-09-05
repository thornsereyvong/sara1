package com.balancika.crm.dao;

import java.util.List;

import com.balancika.crm.model.CrmOpportunityType;

public interface CrmOpportunityTypeDao {

	public boolean insertOpportunityType(CrmOpportunityType type);
	public boolean updateOpportunityType(CrmOpportunityType type);
	public String deleteOpportunityType(int typeID);
	public List<CrmOpportunityType> listOpportunityTypes();
	public CrmOpportunityType findOpportunityTypeById(int typeID);
}
