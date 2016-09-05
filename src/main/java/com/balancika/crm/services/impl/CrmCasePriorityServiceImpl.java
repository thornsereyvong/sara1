package com.balancika.crm.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmCasePriorityDao;
import com.balancika.crm.model.CrmCasePriority;
import com.balancika.crm.services.CrmCasePriorityService;

@Service
@Transactional
public class CrmCasePriorityServiceImpl implements CrmCasePriorityService{

	@Autowired
	private CrmCasePriorityDao priorityDao;
	
	@Override
	public boolean insertCasePriority(CrmCasePriority casePriority) {
		return priorityDao.insertCasePriority(casePriority);
	}

	@Override
	public boolean updateCasePriority(CrmCasePriority casePriority) {
		return priorityDao.updateCasePriority(casePriority);
	}

	@Override
	public String deleteCasePriority(int priorityId) {
		return priorityDao.deleteCasePriority(priorityId);
	}

	@Override
	public List<CrmCasePriority> listCasePriorities() {
		return priorityDao.listCasePriorities();
	}

	@Override
	public CrmCasePriority findCasePriorityById(int priorityId) {
		return priorityDao.findCasePriorityById(priorityId);
	}
}
