package com.balancika.crm.services;

import com.balancika.crm.model.CrmUser;
import com.balancika.crm.model.MeDataSource;

public interface CrmDashboardService {
	Object viewDashboard(String username, MeDataSource dataSource);
	boolean confDashboard(CrmUser user);
}
