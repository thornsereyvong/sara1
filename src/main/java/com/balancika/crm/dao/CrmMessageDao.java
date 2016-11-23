package com.balancika.crm.dao;

import com.balancika.crm.model.MeDataSource;

public interface CrmMessageDao {
	String getMessage(String code, String module, String moduleId, MeDataSource dataSource);
	String getMessage(String code, String module, int moduleId, MeDataSource dataSource);
}
