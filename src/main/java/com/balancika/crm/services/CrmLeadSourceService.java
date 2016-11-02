package com.balancika.crm.services;

import java.util.List;

import com.balancika.crm.model.CrmLeadSource;
import com.balancika.crm.model.MeDataSource;

public interface CrmLeadSourceService {

	public boolean insertLeadSource(CrmLeadSource source);
	public boolean updateLeadSource(CrmLeadSource source);
	public String deleteLeadSource(CrmLeadSource source);
	public List<CrmLeadSource> getAllLeadSource(MeDataSource dataSource);
	public CrmLeadSource findLeadSourceById(int sourceID, MeDataSource dataSource);
}
