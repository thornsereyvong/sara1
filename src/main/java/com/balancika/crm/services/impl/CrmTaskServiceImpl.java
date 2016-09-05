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
		try{
			return taskDao.insertTask(task);
		}catch(Exception e){
			return false;
		}
	}

	@Override
	public boolean updateTask(CrmTask task) {
		try{
			return taskDao.updateTask(task);
		}catch(Exception e){
			return false;
		}
	}

	@Override
	public boolean deleteTask(String taskId) {
		try{
			return taskDao.deleteTask(taskId);
		}catch(Exception e){
			return false;
		}
	}

	@Override
	public List<CrmTask> listTasks() {
		try{
			return taskDao.listTasks();
		}catch(Exception e){
			return null;
		}
	}

	@Override
	public Object findTaskById(String taskId) {
		try{
			return taskDao.findTaskById(taskId);
		}catch(Exception e){
			return null;
		}
	}

	@Override
	public CrmTask findTaskDetailsById(String taskId) {
		try{
			return taskDao.findTaskDetailsById(taskId);
		}catch(Exception e){
			return null;
		}
	}

}
