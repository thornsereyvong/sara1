package com.balancika.crm.services;

import java.util.List;

import com.balancika.crm.model.CrmCasePriority;

public interface CrmCasePriorityService {
	boolean insertCasePriority(CrmCasePriority casePriority);
	boolean updateCasePriority(CrmCasePriority casePriority);
	String deleteCasePriority(int priorityId);
	List<CrmCasePriority> listCasePriorities();
	CrmCasePriority findCasePriorityById(int priorityId);
}
