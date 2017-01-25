package com.balancika.crm.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.configuration.HibernateSessionFactory;
import com.balancika.crm.dao.CrmDashboardDao;
import com.balancika.crm.model.CrmConfDashboard;
import com.balancika.crm.model.CrmUser;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.CrmDashboardService;

@Service
@Transactional
public class CrmDashboardServiceImpl implements CrmDashboardService{

	@Autowired
	private CrmDashboardDao dashboardDao;
	
	@Override
	public Object viewDashboard(String username, MeDataSource dataSource) {
		return dashboardDao.viewDashboard(username, dataSource);
	}

	@Override
	public boolean confDashboard(CrmUser user) {
		return dashboardDao.confDashboard(user);
	}

}
