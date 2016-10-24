package com.balancika.crm.services.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmDatabaseConfigurationDao;
import com.balancika.crm.services.CrmDatabaseConfigurationService;

@Service
@Transactional
public class CrmDatabaseConfigurationServiceImpl implements CrmDatabaseConfigurationService{

	@Autowired
	private CrmDatabaseConfigurationDao configurationDao;

	@Override
	public void changeDataSource(String dbName) {
		configurationDao.changeDataSource(dbName);
	}

	@Override
	public Object listDatabases() {
		return configurationDao.listDatabases();
	}
	

}
