package com.balancika.crm.dao;

import java.util.List;
import com.balancika.crm.model.CrmLead;

public interface CrmLeadDao {

	public boolean insertLead(CrmLead lead);
	public boolean updateLead(CrmLead lead);
	public boolean deleteLead(String leadID);
	public List<CrmLead> getAllLead();
	public Object findLeadById(String leadID);
	CrmLead findLeadDetailById(String leadID);
	public boolean convertLead(String json);
}
