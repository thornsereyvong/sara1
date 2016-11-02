package com.balancika.crm.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmCaseDao;
import com.balancika.crm.model.CrmCase;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.CrmCaseService;

@Service
@Transactional
public class CrmCaseServiceImpl implements CrmCaseService{

	@Autowired
	private CrmCaseDao caseDao;
	
	@Override
	public boolean insertCase(CrmCase cases) {
		return caseDao.insertCase(cases);
	}

	@Override
	public boolean updateCase(CrmCase cases) {
		return caseDao.updateCase(cases);
	}

	@Override
	public boolean deleteCase(CrmCase cases) {
		return caseDao.deleteCase(cases);
	}

	@Override
	public List<CrmCase> listCases(MeDataSource dataSource) {
		return caseDao.listCases(dataSource);
	}

	@Override
	public Object findCaseById(String caseId, MeDataSource dataSource) {
		return caseDao.findCaseById(caseId, dataSource);
	}

	@Override
	public CrmCase findCaseDetailsById(String caseId, MeDataSource dataSource) {
		return caseDao.findCaseDetailsById(caseId, dataSource);
	}
}
