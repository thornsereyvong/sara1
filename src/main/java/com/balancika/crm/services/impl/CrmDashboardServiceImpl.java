package com.balancika.crm.services.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmDashboardDao;
import com.balancika.crm.services.CrmDashboardService;

@Service
@Transactional
public class CrmDashboardServiceImpl implements CrmDashboardService{

	@Autowired
	private CrmDashboardDao dashboardDao;
	
	@Override
	public Object viewDashboard(String username) {
		return dashboardDao.viewDashboard(username);
	}

}
