package com.balancika.crm.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmCaseTypeDao;
import com.balancika.crm.model.CrmCaseType;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.CrmCaseTypeService;

@Service
@Transactional
public class CrmCaseTypeServiceImpl implements CrmCaseTypeService{

	@Autowired
	private CrmCaseTypeDao typeDao;
	
	@Override
	public boolean insertCaseType(CrmCaseType type) {
		return typeDao.insertCaseType(type);
	}

	@Override
	public boolean updateCaseType(CrmCaseType type) {
		return typeDao.updateCaseType(type);
	}

	@Override
	public String deleteCaseType(CrmCaseType type) {
		return typeDao.deleteCaseType(type);
	}

	@Override
	public List<CrmCaseType> listCaseTypes(MeDataSource dataSource) {
		return typeDao.listCaseTypes(dataSource);
	}

	@Override
	public CrmCaseType findCaseTypeById(int typeId, MeDataSource dataSource) {
		return typeDao.findCaseTypeById(typeId, dataSource);
	}
}
