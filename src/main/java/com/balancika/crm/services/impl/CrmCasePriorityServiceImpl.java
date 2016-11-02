package com.balancika.crm.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmCasePriorityDao;
import com.balancika.crm.model.CrmCasePriority;
import com.balancika.crm.model.MeDataSource;
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
	public String deleteCasePriority(CrmCasePriority casePriority) {
		return priorityDao.deleteCasePriority(casePriority);
	}

	@Override
	public List<CrmCasePriority> listCasePriorities(MeDataSource dataSource) {
		return priorityDao.listCasePriorities(dataSource);
	}

	@Override
	public CrmCasePriority findCasePriorityById(int priorityId, MeDataSource dataSource) {
		return priorityDao.findCasePriorityById(priorityId, dataSource);
	}
}
