package com.balancika.crm.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmTaskDao;
import com.balancika.crm.model.CrmTask;
import com.balancika.crm.model.MeDataSource;
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
	public boolean deleteTask(CrmTask task) {
		return taskDao.deleteTask(task);
	}

	@Override
	public List<CrmTask> listTasks(MeDataSource dataSource) {
		return taskDao.listTasks(dataSource);
	}

	@Override
	public Object findTaskById(String taskId, MeDataSource dataSource) {
		return taskDao.findTaskById(taskId, dataSource);
	}

	@Override
	public CrmTask findTaskDetailsById(String taskId, MeDataSource dataSource) {
		return taskDao.findTaskDetailsById(taskId, dataSource);
	}

	@Override
	public List<CrmTask> listTasksRelatedToLead(String leadId, MeDataSource dataSource) {
		return taskDao.listTasksRelatedToLead(leadId, dataSource);
	}

	@Override
	public List<CrmTask> listTasksRelatedToOpportunity(String opId, MeDataSource dataSource) {
		return taskDao.listTasksRelatedToOpportunity(opId, dataSource);
	}

	@Override
	public List<CrmTask> listTasksRelatedToModule(String moduleId, MeDataSource dataSource) {
		return taskDao.listTasksRelatedToModule(moduleId, dataSource);
	}

}
