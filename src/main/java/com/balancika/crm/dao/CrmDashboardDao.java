package com.balancika.crm.dao;

import com.balancika.crm.model.MeDataSource;

public interface CrmDashboardDao {
	Object viewDashboard(String username, MeDataSource dataSource);
}
