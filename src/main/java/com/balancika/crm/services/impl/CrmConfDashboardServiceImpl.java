package com.balancika.crm.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmConfDashboardDao;
import com.balancika.crm.model.CrmConfDashboard;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.CrmConfDashboardService;

@Service
@Transactional
public class CrmConfDashboardServiceImpl implements CrmConfDashboardService{

	@Autowired
	private CrmConfDashboardDao confdao;

	@Override
	public List<CrmConfDashboard> listConf(MeDataSource dataSource) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object findConfById(String conId, MeDataSource dataSource) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertConf(CrmConfDashboard conf) {
		return confdao.insertConf(conf); 
	}

	@Override
	public boolean updateConf(CrmConfDashboard conf) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteConf(CrmConfDashboard conf) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
