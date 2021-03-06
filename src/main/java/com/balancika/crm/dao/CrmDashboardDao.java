package com.balancika.crm.dao;

import com.balancika.crm.model.CrmUser;
import com.balancika.crm.model.MeDataSource;

public interface CrmDashboardDao {
	Object viewDashboard(String username, MeDataSource dataSource);
	boolean confDashboard(CrmUser user);
}
