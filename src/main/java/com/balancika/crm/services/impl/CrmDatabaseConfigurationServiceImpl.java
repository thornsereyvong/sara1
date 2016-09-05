package com.balancika.crm.services.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmDatabaseConfigurationDao;
import com.balancika.crm.model.CrmDatabaseConfiguration;
import com.balancika.crm.services.CrmDatabaseConfigurationService;

@Service
@Transactional
public class CrmDatabaseConfigurationServiceImpl implements CrmDatabaseConfigurationService{

	@Autowired
	private CrmDatabaseConfigurationDao configurationDao;
	
	@Override
	public boolean insertDatabaseConfiguration(CrmDatabaseConfiguration databaseConfiguration) {
		return configurationDao.insertDatabaseConfiguration(databaseConfiguration);
	}

	@Override
	public boolean updateDatabaseConfiguration(CrmDatabaseConfiguration databaseConfiguration) {
		return configurationDao.updateDatabaseConfiguration(databaseConfiguration);
	}

	@Override
	public boolean deleteDatabaseConfiguration(String dbId) {
		return configurationDao.deleteDatabaseConfiguration(dbId);
	}

	@Override
	public CrmDatabaseConfiguration findDatabaseConfigurationById(String dbId) {
		return configurationDao.findDatabaseConfigurationById(dbId);
	}

}
