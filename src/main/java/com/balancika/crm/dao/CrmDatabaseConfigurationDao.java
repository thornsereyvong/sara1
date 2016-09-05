package com.balancika.crm.dao;

import com.balancika.crm.model.CrmDatabaseConfiguration;

public interface CrmDatabaseConfigurationDao {

	boolean insertDatabaseConfiguration(CrmDatabaseConfiguration databaseConfiguration);
	boolean updateDatabaseConfiguration(CrmDatabaseConfiguration databaseConfiguration);
	boolean deleteDatabaseConfiguration(String dbId);
	CrmDatabaseConfiguration findDatabaseConfigurationById(String dbId);
	CrmDatabaseConfiguration createDatabase(CrmDatabaseConfiguration databaseConfiguration);
}
