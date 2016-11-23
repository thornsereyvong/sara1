package com.balancika.crm.services.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmMessageDao;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.CrmMessageService;

@Service
@Transactional
public class CrmMessageServiceImpl implements CrmMessageService{

	@Autowired
	private CrmMessageDao messageDao;
	
	@Override
	public String getMessage(String code, String module, int moduleId, MeDataSource dataSource) {
		return messageDao.getMessage(code, module, moduleId, dataSource);
	}

	@Override
	public String getMessage(String code, String module, String moduleId, MeDataSource dataSource) {
		return messageDao.getMessage(code, module, moduleId, dataSource);
	}

}
