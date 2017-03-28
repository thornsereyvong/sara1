package com.balancika.crm.dao;

import java.util.List;
import java.util.Map;

import com.balancika.crm.model.CrmLead;
import com.balancika.crm.model.MeDataSource;

public interface CrmLeadDao {
	public boolean insertLead(CrmLead lead);
	public boolean updateLead(CrmLead lead);
	public boolean deleteLead(CrmLead lead);
	public List<CrmLead> getAllLead(MeDataSource dataSource);
	public List<CrmLead> getLeadBySpecificUser(String username, MeDataSource dataSource);
	public Object findLeadById(String leadID, MeDataSource dataSource);
	CrmLead findLeadDetailById(String leadID, MeDataSource dataSource);
	boolean updateLeadStatusToConverted(String leadID, String custId, String opId, MeDataSource dataSource);
	Map<String, Object> viewLeadById(String leadId, String userId, MeDataSource dataSource);
	Map<String, Object> convertLeadStartup(String leadId, String userId, MeDataSource dataSource);
	Map<String, Object> editLeadStartup(String leadId, String userId, MeDataSource dataSource);
	Map<String, Object> createLeadStartup(String userId, MeDataSource dataSource);
}
