package com.balancika.crm.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmCaseOriginDao;
import com.balancika.crm.model.CrmCaseOrigin;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.CrmCaseOriginService;

@Service
@Transactional
public class CrmCaseOriginServiceImpl implements CrmCaseOriginService{

	@Autowired
	private CrmCaseOriginDao originDao;
	
	@Override
	public boolean addCaseOrigin(CrmCaseOrigin origin) {
		return originDao.addCaseOrigin(origin);
	}

	@Override
	public boolean updateCaseOrigin(CrmCaseOrigin origin) {
		return originDao.updateCaseOrigin(origin);
	}

	@Override
	public boolean deleteCaseOrigin(CrmCaseOrigin origin) {
		return originDao.deleteCaseOrigin(origin);
	}

	@Override
	public CrmCaseOrigin findCaseOriginById(int originId, MeDataSource dataSource) {
		return originDao.findCaseOriginById(originId, dataSource);
	}

	@Override
	public List<CrmCaseOrigin> listCaseOrigins(MeDataSource dataSource) {
		return originDao.listCaseOrigins(dataSource);
	}

}
