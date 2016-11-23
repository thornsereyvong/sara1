package com.balancika.crm.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmUserActivityDao;
import com.balancika.crm.model.CrmUserActivity;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.CrmUserActivityService;

@Service
@Transactional
public class CrmUserActivityServiceImpl implements CrmUserActivityService{
	
	@Autowired
	private CrmUserActivityDao activityDao;

	@Override
	public boolean addUserActivity(CrmUserActivity activity) {
		return activityDao.addUserActivity(activity);
	}

	@Override
	public List<CrmUserActivity> listUserActivities(MeDataSource dataSource) {
		return activityDao.listUserActivities(dataSource);
	}

	@Override
	public List<CrmUserActivity> listUserActivitiesWithSpecificUser(String username, MeDataSource dataSource) {
		return activityDao.listUserActivitiesWithSpecificUser(username, dataSource);
	}

}
