package com.balancika.crm.dao;

import java.util.List;

import com.balancika.crm.model.CrmCasePriority;

public interface CrmCasePriorityDao {

	boolean insertCasePriority(CrmCasePriority casePriority);
	boolean updateCasePriority(CrmCasePriority casePriority);
	String deleteCasePriority(int priorityId);
	List<CrmCasePriority> listCasePriorities();
	CrmCasePriority findCasePriorityById(int priorityId);
}
