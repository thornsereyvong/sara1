package com.balancika.crm.services;

import java.util.List;

import com.balancika.crm.dao.CrmUserActivityDao;
import com.balancika.crm.model.CrmUserActivity;
import com.balancika.crm.model.MeDataSource;

public interface CrmUserActivityService extends CrmUserActivityDao{
	@Override
	public boolean addUserActivity(CrmUserActivity activity);
	
	@Override
	public List<CrmUserActivity> listUserActivities(MeDataSource dataSource);
	
	@Override
	public List<CrmUserActivity> listUserActivitiesWithSpecificUser(String username, MeDataSource dataSource);
}
