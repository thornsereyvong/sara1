package com.balancika.crm.services;

import java.util.List;

import com.balancika.crm.model.CrmTaskStatus;

public interface CrmTaskStatusService {
	boolean insertTaskStatus(CrmTaskStatus status);
	boolean updateTaskStatus(CrmTaskStatus status);
	String deleteTaskStatus(int statusId);
	List<CrmTaskStatus> lisTaskStatus();
	CrmTaskStatus findTaskStatusById(int statusId);
}
