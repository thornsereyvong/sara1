package com.balancika.crm.services;

import java.util.List;
import java.util.Map;

import com.balancika.crm.model.CrmLead;
import com.balancika.crm.model.MeDataSource;

public interface CrmLeadService {

	public boolean insertLead(CrmLead lead);
	public boolean updateLead(CrmLead lead);
	public boolean deleteLead(CrmLead lead);
	public List<CrmLead> getAllLead(MeDataSource dataSource);
	public List<CrmLead> getLeadBySpecificUser(String username, MeDataSource dataSource);
	public Object findLeadById(String leadID, MeDataSource dataSource);
	CrmLead findLeadDetailById(String leadID, MeDataSource dataSource);
	Map<String,Object> viewActivitiesOfLeadById(String leadId, MeDataSource dataSource);
	boolean updateLeadStatusToConverted(String leadID, String custId, String opId, MeDataSource dataSource);
}
