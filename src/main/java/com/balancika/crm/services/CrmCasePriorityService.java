package com.balancika.crm.services;

import java.util.List;

import com.balancika.crm.model.CrmCasePriority;
import com.balancika.crm.model.MeDataSource;

public interface CrmCasePriorityService {
	boolean insertCasePriority(CrmCasePriority casePriority);
	boolean updateCasePriority(CrmCasePriority casePriority);
	String deleteCasePriority(CrmCasePriority casePriority);
	List<CrmCasePriority> listCasePriorities(MeDataSource dataSource);
	CrmCasePriority findCasePriorityById(int priorityId, MeDataSource dataSource);
}
