package com.balancika.crm.services;

import java.util.List;
import java.util.Map;

import com.balancika.crm.model.CrmLead;

public interface CrmLeadService {

	public boolean insertLead(CrmLead lead);
	public boolean updateLead(CrmLead lead);
	public boolean deleteLead(String leadID);
	public List<CrmLead> getAllLead();
	public List<CrmLead> getLeadBySpecificUser(String username);
	public Object findLeadById(String leadID);
	CrmLead findLeadDetailById(String leadID);
	public boolean convertLead(String json);
	Map<String,Object> viewActivitiesOfLeadById(String leadId);
}
