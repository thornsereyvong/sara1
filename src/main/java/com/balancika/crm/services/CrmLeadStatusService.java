package com.balancika.crm.services;

import java.util.List;

import com.balancika.crm.model.CrmLeadStatus;

public interface CrmLeadStatusService {

	public boolean insertLeadStatus(CrmLeadStatus status);
	public boolean updateLeadStatus(CrmLeadStatus status);
	public String deleteLeadStatus(int statusID);
	public List<CrmLeadStatus> getAllLeadStatus();
	public CrmLeadStatus findLeadStatusById(int statusID);
}
