package com.balancika.crm.dao;

import java.util.Map;

import com.balancika.crm.model.MeDataSource;

public interface CompanyDao {
	Object listDatabases(MeDataSource meDataSource);
	Map<String, Object> listDatabaseForMobile(int pageSize, int pageNumber, MeDataSource dataSource);
	Map<String, Object> searchCompanyNameSuggestion(String str, MeDataSource dataSource);
}
