package com.balancika.crm.dao.impl;

import org.springframework.stereotype.Repository;

import com.balancika.crm.dao.CrmDatabaseConfigurationDao;
import com.balancika.crm.model.CrmDatabaseConfiguration;

@Repository
public class CrmDatabaseConfigurationDaoImpl implements CrmDatabaseConfigurationDao{

	@Override
	public void changeDataSource(String dbName) {
		new CrmDatabaseConfiguration().setDbName(dbName);
	}
}
