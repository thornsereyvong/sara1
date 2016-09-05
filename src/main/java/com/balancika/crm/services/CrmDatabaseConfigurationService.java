package com.balancika.crm.services;

import com.balancika.crm.model.CrmDatabaseConfiguration;

public interface CrmDatabaseConfigurationService {
	boolean insertDatabaseConfiguration(CrmDatabaseConfiguration databaseConfiguration);
	boolean updateDatabaseConfiguration(CrmDatabaseConfiguration databaseConfiguration);
	boolean deleteDatabaseConfiguration(String dbId);
	CrmDatabaseConfiguration findDatabaseConfigurationById(String dbId);
}
