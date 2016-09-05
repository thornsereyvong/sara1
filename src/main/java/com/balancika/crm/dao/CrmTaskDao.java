package com.balancika.crm.dao;

import java.util.List;

import com.balancika.crm.model.CrmTask;

public interface CrmTaskDao {

	boolean insertTask(CrmTask task);
	boolean updateTask(CrmTask task);
	boolean deleteTask(String taskId);
	List<CrmTask> listTasks();
	Object findTaskById(String taskId);
	CrmTask findTaskDetailsById(String taskId);
}
