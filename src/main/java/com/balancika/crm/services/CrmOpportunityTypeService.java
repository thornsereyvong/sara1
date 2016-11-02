package com.balancika.crm.services;

import java.util.List;

import com.balancika.crm.model.CrmOpportunityType;
import com.balancika.crm.model.MeDataSource;

public interface CrmOpportunityTypeService {

	public boolean insertOpportunityType(CrmOpportunityType type);
	public boolean updateOpportunityType(CrmOpportunityType type);
	public String deleteOpportunityType(CrmOpportunityType type);
	public List<CrmOpportunityType> listOpportunityTypes(MeDataSource dataSource);
	public CrmOpportunityType findOpportunityTypeById(int typeID, MeDataSource dataSource);
}
