package com.balancika.crm.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmTaskStatusDao;
import com.balancika.crm.model.CrmTaskStatus;
import com.balancika.crm.services.CrmTaskStatusService;

@Service
@Transactional
public class CrmTaskStatusServiceImpl implements CrmTaskStatusService{

	@Autowired
	private CrmTaskStatusDao statusDao;

	@Override
	public boolean insertTaskStatus(CrmTaskStatus status) {
		return statusDao.insertTaskStatus(status);
	}

	@Override
	public boolean updateTaskStatus(CrmTaskStatus status) {
		return statusDao.updateTaskStatus(status);
	}

	@Override
	public String deleteTaskStatus(int statusId) {
		return statusDao.deleteTaskStatus(statusId);
	}

	@Override
	public List<CrmTaskStatus> lisTaskStatus() {
		return statusDao.lisTaskStatus();
	}

	@Override
	public CrmTaskStatus findTaskStatusById(int statusId) {
		return statusDao.findTaskStatusById(statusId);
	}
}
