package com.balancika.crm.services;

import java.util.List;

import com.balancika.crm.model.CrmLeadSource;

public interface CrmLeadSourceService {

	public boolean insertLeadSource(CrmLeadSource source);
	public boolean updateLeadSource(CrmLeadSource source);
	public String deleteLeadSource(int sourceID);
	public List<CrmLeadSource> getAllLeadSource();
	public CrmLeadSource findLeadSourceById(int sourceID);
}
