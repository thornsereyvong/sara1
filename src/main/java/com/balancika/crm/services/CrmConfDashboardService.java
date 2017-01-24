package com.balancika.crm.services;

import java.util.List;

import com.balancika.crm.model.CrmConfDashboard;
import com.balancika.crm.model.MeDataSource;

public interface CrmConfDashboardService {
	boolean insertConf(CrmConfDashboard conf);
	boolean updateConf(CrmConfDashboard conf);
	boolean deleteConf(CrmConfDashboard conf);
	List<CrmConfDashboard> listConf(MeDataSource dataSource);
	Object findConfById(String conId, MeDataSource dataSource);
}
