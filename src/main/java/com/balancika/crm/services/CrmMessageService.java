package com.balancika.crm.services;

import com.balancika.crm.dao.CrmMessageDao;
import com.balancika.crm.model.MeDataSource;

public interface CrmMessageService extends CrmMessageDao{
	@Override
	public String getMessage(String code, String module, int moduleId, MeDataSource dataSource);
	
	@Override
	public String getMessage(String code, String module, String moduleId, MeDataSource dataSource);
}
