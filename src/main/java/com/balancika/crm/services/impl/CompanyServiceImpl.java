package com.balancika.crm.services.impl;

import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CompanyDao;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.CompanyService;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService{

	@Autowired
	private CompanyDao configurationDao;

	@Override
	public Object listDatabases(MeDataSource dataSource) {
		return configurationDao.listDatabases(dataSource);
	}

	@Override
	public Map<String, Object> listDatabaseForMobile(int pageSize, int pageNumber, MeDataSource dataSource) {
		return configurationDao.listDatabaseForMobile(pageSize, pageNumber, dataSource);
	}

	@Override
	public Map<String, Object> searchCompanyNameSuggestion(String str, MeDataSource dataSource) {
		return configurationDao.searchCompanyNameSuggestion(str, dataSource);
	}

}
