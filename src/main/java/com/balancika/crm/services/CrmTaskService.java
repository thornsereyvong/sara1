package com.balancika.crm.services;

import java.util.List;

import com.balancika.crm.model.CrmTask;
import com.balancika.crm.model.MeDataSource;

public interface CrmTaskService {
	boolean insertTask(CrmTask task);
	boolean updateTask(CrmTask task);
	boolean deleteTask(CrmTask task);
	List<CrmTask> listTasks(MeDataSource dataSource);
	Object findTaskById(String taskId, MeDataSource dataSource);
	CrmTask findTaskDetailsById(String taskId, MeDataSource dataSource);
	List<CrmTask> listTasksRelatedToLead(String leadId, MeDataSource dataSource);
	List<CrmTask> listTasksRelatedToOpportunity(String opId, MeDataSource dataSource);
	List<CrmTask> listTasksRelatedToModule(String moduleId, MeDataSource dataSource);
}
