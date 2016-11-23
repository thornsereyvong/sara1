package com.balancika.crm.dao;

import java.util.List;

import com.balancika.crm.model.CrmUserActivity;
import com.balancika.crm.model.MeDataSource;

public interface CrmUserActivityDao {
	boolean addUserActivity(CrmUserActivity activity);
	List<CrmUserActivity> listUserActivities(MeDataSource dataSource);
	List<CrmUserActivity> listUserActivitiesWithSpecificUser(String username, MeDataSource dataSource);
}
