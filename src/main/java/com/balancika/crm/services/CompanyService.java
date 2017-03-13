package com.balancika.crm.services;

import java.util.Map;

import com.balancika.crm.model.MeDataSource;

public interface CompanyService {
	Object listDatabases(MeDataSource dataSource);
	Map<String, Object> listDatabaseForMobile(int pageSize, int pageNumber, MeDataSource dataSource);
	Map<String, Object> searchCompanyNameSuggestion(String str, MeDataSource dataSource);
}
