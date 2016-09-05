package com.balancika.crm.dao;

import java.util.List;

import com.balancika.crm.model.CrmLeadStatus;

public interface CrmLeadStatusDao{

	public boolean insertLeadStatus(CrmLeadStatus status);
	public boolean updateLeadStatus(CrmLeadStatus status);
	public String deleteLeadStatus(int statusID);
	public List<CrmLeadStatus> getAllLeadStatus();
	public CrmLeadStatus findLeadStatusById(int statusID);
}
