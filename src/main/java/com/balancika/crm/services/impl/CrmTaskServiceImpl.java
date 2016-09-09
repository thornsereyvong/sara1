package com.balancika.crm.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmTaskDao;
import com.balancika.crm.model.CrmTask;
import com.balancika.crm.services.CrmTaskService;

@Service
@Transactional
public class CrmTaskServiceImpl implements CrmTaskService{

	@Autowired
	private CrmTaskDao taskDao;
	
	@Override
	public boolean insertTask(CrmTask task) {
		return taskDao.insertTask(task);
	}

	@Override
	public boolean updateTask(CrmTask task) {
		return taskDao.updateTask(task);
	}

	@Override
	public boolean deleteTask(String taskId) {
		return taskDao.deleteTask(taskId);
	}

	@Override
	public List<CrmTask> listTasks() {
		return taskDao.listTasks();
	}

	@Override
	public Object findTaskById(String taskId) {
		return taskDao.findTaskById(taskId);
	}

	@Override
	public CrmTask findTaskDetailsById(String taskId) {
		return taskDao.findTaskDetailsById(taskId);
	}

	@Override
	public List<CrmTask> listTasksRelatedToLead(String leadId) {
		return taskDao.listTasksRelatedToLead(leadId);
	}

	@Override
	public List<CrmTask> listTasksRelatedToOpportunity(String opId) {
		return taskDao.listTasksRelatedToOpportunity(opId);
	}

}
