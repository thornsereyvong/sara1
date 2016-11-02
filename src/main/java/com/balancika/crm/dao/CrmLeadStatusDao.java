package com.balancika.crm.dao;

import java.util.List;

import com.balancika.crm.model.CrmLeadStatus;
import com.balancika.crm.model.MeDataSource;

public interface CrmLeadStatusDao{

	public boolean insertLeadStatus(CrmLeadStatus status);
	public boolean updateLeadStatus(CrmLeadStatus status);
	public String deleteLeadStatus(CrmLeadStatus status);
	public List<CrmLeadStatus> getAllLeadStatus(MeDataSource dataSource);
	public CrmLeadStatus findLeadStatusById(int statusID, MeDataSource dataSource);
}
